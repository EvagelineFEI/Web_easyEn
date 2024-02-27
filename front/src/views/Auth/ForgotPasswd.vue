<script setup lang="ts">
const email = ref("");
const isFormValid = ref(true);
const emailRules = ref([
    (v: string) => !!v || "E-mail is required",
    (v: string) => /.+@.+\..+/.test(v) || "E-mail must be valid",
    ]);

// forgot in buttons
const isLoading = ref(false);
const isSignInDisabled = ref(false);

const handleForgot = async () => {

}

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
    <v-card color="white" class="pa-3 ma-3" elevation="3">
      <v-card-title class="my-4 text-h4">
        <span class="flex-fill"> Frogot </span>
      </v-card-title>
      <v-card-subtitle>输入你的账号邮箱</v-card-subtitle>
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
              @keyup.enter="handleForgot"
              @change="resetErrors"
          ></v-text-field>
          <v-btn
              :loading="isLoading"
              :disabled="isSignInDisabled"
              block
              size="x-large"
              color="primary"
              @click="handleForgot"
              class="mt-2"
          >
            {{ $t("login.button") }}
          </v-btn>

          <div v-if="errorProvider" class="error--text my-2">
            {{ errorProviderMessages }}
          </div>
        </v-form>
      </v-card-text>
    </v-card>
  </div>
</template>

<style>
.layout-content {
  max-width: 480px;
}
</style>