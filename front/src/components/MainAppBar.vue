<script setup lang="ts">

import {useDisplay} from "vuetify";
import UserProfile from "@/components/UserProfile";
import configs from "@/configs";

const customizeTheme = configs.stores.useCustomizeThemeStore();
const navListBar = configs.menu.appBarNav;
const { mdAndUp } = useDisplay();
const authStore = configs.stores.useAuthStore();
const getLogo = () => {
  return new URL(`@/assets/EAZYEN.png`, import.meta.url).href
}

const props = defineProps({
  menu: {
    default: () => [],
  },
});

</script>

<template>
  <v-system-bar height="65">
    <v-img :src="getLogo()" max-width="230" max-height="140" alt="EAZYEN" class="mx-2" contain/>
    <v-spacer></v-spacer>
  </v-system-bar>
    <v-app-bar
        color="primary"
        :density="mdAndUp ? 'default' : 'compact'"
        flat
    >
      <v-spacer/>
      <v-toolbar-items>
        <v-btn v-for="nav in navListBar" :key="nav.title" :to="nav.to" :prepend-icon="nav.icon ? nav.icon :''" variant="text">
          {{ nav.title }}
        </v-btn>
      </v-toolbar-items>

      <v-spacer v-for="i in 3"/>

      <v-divider color="primary" :thickness="14" :vertical="true"/>
      <UserProfile v-if="authStore.isLoggedIn"/>
      <v-btn class="mx-1" variant="flat" :to="{'name': 'SignIn'}" prepend-icon="mdi-login" v-else>
        {{$t('login.title')}}
      </v-btn>
      <v-divider color="primary" :thickness="14" :vertical="true"/>
    </v-app-bar>
</template>