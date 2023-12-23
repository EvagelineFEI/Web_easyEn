<script lang="ts" setup>
import {ref} from "vue";
import auth from "@/api/auth";
import type {AuthStruct} from "@/api/auth.ts";
import router from "@/router";

// 组件状态
const isLoading = ref(false);
const isSignInDisabled = ref(false);
const isRequireCodeDisabled = ref(false)
const isFormValid = ref(true);
const showPassword = ref(false);
const disabled = ref(true);
const step = ref(1);
const snackColor = ref('')

// 重要参数
const email = ref("");
const vertificateCode = ref("");
const password = ref("");
const confirmPassword = ref("");
const isInterval = ref(false);
const time = ref(60);
const content = ref('发送验证码')

const sendCodeHandle = async () => {
  snackProvider.value = false;
  snackProviderMessages.value = '';

  if (error.value) {
    return
  }

  isLoading.value = true;
  isRequireCodeDisabled.value = true;

  const params = {
    email: email.value,
  }

  let code = 0;
  let msg = '';


  await auth.getCheckCode(
    params
  ).then((response) => {
    code = response.code;
    msg = code === 200 ? response.resultData : response.msg;
  })

  if (code != 200) {
    errorHandle(msg);
    isRequireCodeDisabled.value = false;
    return;
  }

  isLoading.value = false;
  snackProviderMessages.value = "邮件发送成功，请查收验证码";
  snackColor.value = "success";
  snackProvider.value = true;

  isInterval.value = !isInterval.value;
  await countDown();
  window.setTimeout(() => {
    disabled.value = false;
    step.value = 2;
  }, 2000);
}

async function countDown() {
  content.value = time.value + 's后可重试' //这里解决60秒不见了的问题
  let clock = window.setInterval(() => {
    time.value--
    content.value = time.value + 's后重新发送'
    if (time.value < 0) {     //当倒计时小于0时清除定时器
      window.clearInterval(clock)
      content.value = '发送验证码'
      time.value = 60
      isRequireCodeDisabled.value = false;
    }
  },1000)
}

const resetHandle = async () => {
  snackProvider.value = false;
  snackProviderMessages.value = '';

  if (password.value != confirmPassword.value) {
    error.value = true;
    errorMessagesConfirm.value = "密码不正确，请检查";
    return;
  }

  if (error.value) {
    return
  }

  isLoading.value = true;
  isSignInDisabled.value = true;

  const data: AuthStruct = {
    email: email.value,
    password: password.value,
  }

  const params = {
    code: vertificateCode.value,
  }

  let code = 0;

  let msg = '';

  await auth.newPassword(
    data,
    params
  ).then((response) => {
    code = response.code;
    msg = code === 200 ? response.resultData : response.msg;
  })

  if (code != 200) {
    errorHandle(msg)
    return
  }

  isLoading.value = false;
  isSignInDisabled.value = false;
  snackProviderMessages.value = "重置成功，即将返回登录页面";
  snackColor.value = "success";
  snackProvider.value = true;

  window.setTimeout(() => {
    router.push("/")
  }, 2000);
};

function errorHandle(errorMsg: string) {
  error.value = true;
  snackProvider.value = true;
  snackColor.value = "error";
  snackProviderMessages.value = errorMsg;
  isLoading.value = false;
  isSignInDisabled.value = false;
}


// 邮件密码规则检查
const emailRules = ref([
  (v: string) => !!v || "E-mail is required",
  (v: string) => /.+@.+\..+/.test(v) || "E-mail must be valid",
  ]);

const passwordRules = ref([
  (v: string) => !!v || "Password is required",
  (v: string) =>
      (v && v.length >= 8) || "Password must be more than 8 characters",
  ]);


// error provider
const snackProvider = ref(false);
const snackProviderMessages = ref("");

const error = ref(false);
const errorMessages = ref("");
const errorMessagesConfirm = ref("");

const resetErrors = () => {
  error.value = false;
  errorMessages.value = "";
  errorMessagesConfirm.value = "";
};

</script>

<template>
<div class="layout-content ma-auto w-full">
  <v-stepper
      :disabled="disabled"
      color="primary"
      prev-text="上一步"
      next-text="下一步"
      :model-value="step"
      :items="['输入账号', '重置密码']"
      class="layout-content rounded-lg"
  >
    <template v-slot:item.1>
      <v-card class="rounded-lg" elevation="3">
        <v-card-title class="my-4 text-h4">
          <span class="flex-fill"> 请求验证码 </span>
        </v-card-title>
        <v-card-subtitle>输入账号获取验证码</v-card-subtitle>
        <v-card-text>

          <v-form
              v-model="isFormValid"
              class="text-left"
              validate-on="input"
              @submit.prevent="sendCodeHandle"
          >
            <v-text-field
                v-model="email"
                :error="error"
                :rules="emailRules"
                bg-color="#fff"
                color="primary"
                density="default"
                label="邮件"
                name="email"
                outlined
                validateOn="blur"
                variant="underlined"
                @change="resetErrors"
            ></v-text-field>
            <v-btn
                :loading="isLoading"
                block
                :disabled="isRequireCodeDisabled"
                class="mt-2"
                color="primary"
                size="x-large"
                type="submit"
            >
              {{ content }}
            </v-btn>
          </v-form>
        </v-card-text>
      </v-card>
    </template>
    <template v-slot:item.2>
      <v-card class="rounded-lg" elevation="3">
        <v-card-title class="my-4 text-h4">
          <span class="flex-fill"> 重置密码 </span>
        </v-card-title>
        <v-card-subtitle>输入验证码重置密码</v-card-subtitle>
        <v-card-text>

          <v-form
              v-model="isFormValid"
              :disabled="isSignInDisabled"
              class="text-left"
              validate-on="input"
              @submit.prevent="resetHandle"
          >
            <v-otp-input
                v-model="vertificateCode"
                :error="error"
                @change="resetErrors"
            ></v-otp-input>

            <v-text-field
                v-model="password"
                :append-inner-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                :error="error"
                :error-messages="errorMessages"
                :rules="passwordRules"
                :type="showPassword ? 'text' : 'password'"
                bg-color="#fff"
                color="primary"
                density="default"
                label="密码"
                name="password"
                outlined
                validateOn="blur"
                variant="underlined"
                @change="resetErrors"
                @click:append-inner="showPassword = !showPassword"
            ></v-text-field>

            <v-text-field
                v-model="confirmPassword"
                :error="error"
                :error-messages="errorMessagesConfirm"
                bg-color="#fff"
                color="primary"
                density="default"
                label="确认密码"
                name="confirmPasswd"
                outlined
                type="password"
                validateOn="blur"
                variant="underlined"
                @change="resetErrors"
            ></v-text-field>

            <v-btn
                :loading="isLoading"
                block
                class="mt-2"
                color="primary"
                size="x-large"
                type="submit"
            >
              重置密码
            </v-btn>

          </v-form>
        </v-card-text>
      </v-card>
    </template>
  </v-stepper>

  <v-snackbar
      v-model="snackProvider"
      :timeout="2000"
      :color="snackColor">
    {{ snackProviderMessages }}
    <template v-slot:actions>
      <v-btn
          variant="text"
          @click="snackProvider = false">
        关闭
      </v-btn>
    </template>
  </v-snackbar>
</div>
</template>

<style lang="scss" scoped>
.layout-side {
  width: 420px;
}

.layout-content {
  width: 420px;
}
</style>