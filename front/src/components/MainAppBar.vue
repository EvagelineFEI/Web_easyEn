<script lang="ts" setup>

import {useDisplay} from "vuetify";
import UserProfile from "@/components/UserProfile.vue";
import configs from "@/configs";

const navListBar = configs.menu.appBarNav;
const {mdAndUp} = useDisplay();
const authStore = configs.stores.useAuthStore();
const getLogo = () => {
  return new URL(`@/assets/EAZYEN.png`, import.meta.url).href
}
</script>

<template>
  <v-system-bar height="80">
    <v-img :src="getLogo()" alt="EAZYEN" class="mx-2" contain max-height="280" max-width="250"/>
    <v-spacer></v-spacer>
  </v-system-bar>
  <v-app-bar
      :density="mdAndUp ? 'default' : 'compact'"
      color="primary"
      flat
  >
    <v-spacer/>
    <v-toolbar-items>
      <v-btn v-for="nav in navListBar" :key="nav.title" :prepend-icon="nav.icon ? nav.icon :''" :to="nav.to"
             variant="text">
        {{ nav.title }}
      </v-btn>
    </v-toolbar-items>

    <v-spacer v-for="i in 3"/>

    <v-divider :thickness="14" :vertical="true" color="primary"/>
    <UserProfile v-if="authStore.isLoggedIn"/>
    <v-btn v-else :to="{'name': 'SignIn'}" class="mx-1" prepend-icon="mdi-login" variant="flat">
      {{ $t('login.title') }}
    </v-btn>
    <v-divider :thickness="14" :vertical="true" color="primary"/>
  </v-app-bar>
</template>