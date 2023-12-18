<script setup lang="ts">
import configs from "@/configs";
import {ref} from "vue";
import auth from "@/api/auth";
import type {AuthStruct} from "@/api/auth";
import router from "@/router";

const authStore = configs.stores.useAuthStore();

// sign in buttons
const isLoading = ref(false);
const isSignInDisabled = ref(false);

const refLoginForm = ref();
const isFormValid = ref(true);

const username = ref("");
const email = ref("");
const password = ref("");

// show password field
const showPassword = ref(false);

// snack provider
const snackColor = ref('')
const snackProvider = ref(false);
const snackProviderMessages = ref("");

// Submit
const handleRegister = async () => {
  snackProvider.value = false;
  snackProviderMessages.value = '';

  if (!isFormValid) {
    return
  }

  isLoading.value = true;
  isSignInDisabled.value = true;

  const data: AuthStruct = {
    username: username.value,
    email: email.value,
    password: password.value,
    other_info: ''
  }

  await auth.register(data)
    .then((response) => {
      if (response.code === 200) {
        isLoading.value = false;
        isSignInDisabled.value = false;
        handleSnackBar(0, '注册成功, 即将前往登录页');
        setTimeout(() => {
          router.push("/auth/signin");
        }, 1000);
      } else {
        handleSnackBar(response.code, response.msg);
      }
    })

  isLoading.value = false;
  isSignInDisabled.value = false;
};

function handleSnackBar(status: number, msg: string) {
  snackProvider.value = true;
  snackProviderMessages.value = msg;
  snackColor.value = status === 0 ? 'success' : 'error';
}

// Error Check
const emailRules = ref([
  (v: string) => !!v || "E-mail is required",
  (v: string) => /.+@.+\..+/.test(v) || "E-mail must be valid",
]);

const usernameRules = ref([(v: string) => !!v || "UserNmae is required"]);

const passwordRules = ref([
  (v: string) => !!v || "Password is required",
  (v: string) =>
      (v && v.length >= 8) || "Password must be more than 8 characters",
]);

// error provider
const errorProvider = ref(false);
const errorProviderMessages = ref("");

const error = ref(false);
const errorMessages = ref("");

const resetErrors = () => {
  error.value = false;
  errorMessages.value = "";
};
</script>

<template>
  <div class="layout-content ma-auto w-full">

  <v-card color="white" class="pa-3 ma-3 rounded-lg" elevation="3">
    <v-card-title primary-title class="my-4 text-h4">
      <span class="flex-fill"> {{ $t("register.title") }} </span>
    </v-card-title>
    <v-card-subtitle>Let's build amazing products</v-card-subtitle>
    <!-- sign in form -->

    <v-card-text>
      <v-form
          ref="refLoginForm"
          class="text-left"
          v-model="isFormValid"
          lazy-validation
          @submit.prevent="handleRegister"
      >
        <v-text-field
            v-model="username"
            required
            :error="error"
            :label="$t('register.username')"
            density="default"
            variant="underlined"
            color="primary"
            bg-color="#fff"
            :rules="usernameRules"
            name="username"
            outlined
            validateOn="blur"
            @keyup.enter="handleRegister"
            @change="resetErrors"
        ></v-text-field>
        <v-text-field
            v-model="email"
            required
            :error="error"
            :label="$t('register.email')"
            density="default"
            variant="underlined"
            color="primary"
            bg-color="#fff"
            :rules="emailRules"
            name="email"
            outlined
            validateOn="blur"
            @keyup.enter="handleRegister"
            @change="resetErrors"
        ></v-text-field>
        <v-text-field
            v-model="password"
            :append-inner-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
            :type="showPassword ? 'text' : 'password'"
            :error="error"
            :error-messages="errorMessages"
            :label="$t('register.password')"
            density="default"
            variant="underlined"
            color="primary"
            bg-color="#fff"
            :rules="passwordRules"
            name="password"
            outlined
            validateOn="blur"
            @change="resetErrors"
            @keyup.enter="handleRegister"
            @click:append-inner="showPassword = !showPassword"
        ></v-text-field>
        <v-btn
            :loading="isLoading"
            block
            class="mt-2"
            color="primary"
            size="x-large"
            type="submit"
        >{{ $t("register.button") }}</v-btn>

        <div v-if="errorProvider" class="error--text my-5">
          {{ errorProviderMessages }}
        </div>

        <div class="my-5 text-center">
          {{ $t("register.agree") }}
          <br />
          <router-link class="text-primary" to="">{{
              $t("common.tos")
            }}</router-link>
          &
          <router-link class="text-primary" to="">{{
              $t("common.policy")
            }}</router-link>
        </div>
      </v-form>
    </v-card-text>
  </v-card>
  <div class="text-center mt-6">
    {{ $t("register.account") }}
    <router-link to="/auth/signin" class="text-primary font-weight-bold">
      {{ $t("register.signin") }}
    </router-link>
  </div>
</div>
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
</template>

<style lang="scss" scoped>
.layout-side {
  width: 420px;
}

.layout-content {
  max-width: 480px;
}
</style>