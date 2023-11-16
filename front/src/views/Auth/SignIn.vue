<script setup lang="ts">
import configs from "@/configs";
import {ref} from "vue";


const authStore = configs.stores.useAuthStore();
const isLoading = ref(false);
const isSignInDisabled = ref(false);

const refLoginForm = ref();
const email = ref("");
const password = ref("");
const isFormValid = ref(true);

// show password field
const showPassword = ref(false);

const handleLogin = async () => {
  const {valid} = await refLoginForm.value.validate();
  if (valid) {
    isLoading.value = true;
    isSignInDisabled.value = true;
    await authStore.loginWithEmailAndPassword(email.value, password.value);
  } else {
    console.log("no");
  }
};

const emailRules = ref([
  (v: string) => !!v || "E-mail is required",
  (v: string) => /.+@.+\..+/.test(v) || "E-mail must be valid",
]);

const passwordRules = ref([
  (v: string) => !!v || "Password is required",
  (v: string) =>
      (v && v.length <= 10) || "Password must be less than 10 characters",
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
      <v-card-title class="my-4 text-h4">
        <span class="flex-fill"> Welcome </span>
      </v-card-title>
      <v-card-subtitle>Sign in to your account</v-card-subtitle>
      <!-- sign in form -->

      <v-card-text>
        <v-form
            ref="refLoginForm"
            class="text-left"
            v-model="isFormValid"
            lazy-validation
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
              validateOn="blur"
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
              validateOn="blur"
              @change="resetErrors"
              @keyup.enter="handleLogin"
              @click:append-inner="showPassword = !showPassword"
          ></v-text-field>
          <v-btn
              :loading="isLoading"
              :disabled="isSignInDisabled"
              block
              size="x-large"
              color="primary"
              @click="handleLogin"
              class="mt-2"
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
      </v-card-text
      >
    </v-card>
    <div class="text-center mt-6">
      {{ $t("login.noaccount")}}
      <router-link to="/auth/signup" class="text-primary font-weight-bold">
        {{ $t("login.create") }}
      </router-link>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.layout-side {
  width: 420px;
}

.layout-content {
  max-width: 480px;
}
</style>