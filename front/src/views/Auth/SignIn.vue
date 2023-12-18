<script setup lang="ts">
import configs from "@/configs";
import {ref} from "vue";
import auth from "@/api/auth";
import type {AuthStruct} from "@/api/auth";
import {Response} from "@/api/utils";
import router from "@/router";

const authStore = configs.stores.useAuthStore();
const isLoading = ref(false);
const isSignInDisabled = ref(false);
const isFormValid=ref(true);

const email = ref("");
const password = ref("");

// show password field
const showPassword = ref(false);

// snack provider
const snackColor = ref('')
const snackProvider = ref(false);
const snackProviderMessages = ref("");

const handleLogin = async () => {
  isLoading.value = true;
  isSignInDisabled.value = true;

  const data: AuthStruct = {
    email: email.value,
    password: password.value
  }

  try {
    await auth.login(data)
    .then((response) => {
      if (Number(response.code) === 200) {
        const data = response.resultData as AuthStruct;
        authStore.loggedIn(
          data.token as string,
          Number(data.user_id)
        );
        router.push("/");
      } else {
        errorHandle(response.msg as string);
      }
    })
  } catch {
    errorHandle("意外错误发生");
  }

  isLoading.value = false;
  isSignInDisabled.value = false;
};

const emailRules = ref([
  (v: string) => !!v || "E-mail is required",
  (v: string) => /.+@.+\..+/.test(v) || "E-mail must be valid",
]);

const passwordRules = ref([
  (v: string) => !!v || "Password is required",
  (v: string) =>
      (v && v.length >= 6) || "Password must be more than 6 characters",
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

function errorHandle(errorMsg: string) {
  error.value = true;
  snackColor.value = "error";
  snackProvider.value = true;
  snackProviderMessages.value = errorMsg;
}
</script>

<template>
  <div class="layout-content ma-auto w-full">
    <v-card color="white" class="pa-3 ma-3 rounded-lg" elevation="3">
      <v-card-title class="my-4 text-h4">
        <span class="flex-fill"> Welcome </span>
      </v-card-title>
      <v-card-subtitle>Sign in to your account</v-card-subtitle>
      <v-card-text>
        <v-form
            v-model="isFormValid"
            class="text-left"
            @submit.prevent="handleLogin"
            validate-on="input"
        >
          <v-text-field
              ref="refEmail"
              v-model="email"
              required
              :error="error"
              :label="$t('login.email')"
              density="default"
              variant="underlined"
              color="primary"
              bg-color="#fff"
              :rules="emailRules"
              name="email"
              outlined
              validateOn="input"
              @keyup.enter="handleLogin"
              @change="resetErrors"
          ></v-text-field>
          <v-text-field
              ref="refPassword"
              v-model="password"
              :append-inner-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
              :type="showPassword ? 'text' : 'password'"
              :error="error"
              :error-messages="errorMessages"
              :label="$t('login.password')"
              density="default"
              variant="underlined"
              color="primary"
              bg-color="#fff"
              :rules="passwordRules"
              name="password"
              outlined
              validateOn="input"
              @change="resetErrors"
              @keyup.enter="handleLogin"
              @click:append-inner="showPassword = !showPassword"
          ></v-text-field>
          <v-btn
              :loading="isLoading"
              block
              class="mt-2"
              color="primary"
              size="x-large"
              type="submit"
          >
            {{ $t("login.button") }}
          </v-btn>

          <div v-if="errorProvider" class="error--text my-2">
            {{ errorProviderMessages }}
          </div>

          <div class="mt-5 text-center">
            <router-link class="text-primary" to="/auth/forgot-password">
                          {{ $t("login.forgot") }}
            </router-link>
          </div>
        </v-form>
      </v-card-text>
    </v-card>
    <div class="text-center mt-6">
      {{ $t("login.noaccount")}}
      <router-link to="/auth/signup" class="text-primary font-weight-bold">
        {{ $t("login.create") }}
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