<script setup lang="ts">
import CustomizationMenu from "@/components/CustomizationMenu";
import DefaultLayout from "@/layouts/DefaultLayout";
import CommunicationSidebarLayout from "@/layouts/CommunicateSidebarLayout";
import LearnSidebarLayout from "@/layouts/LearnSidebarLayout";
import ErrorLayout from "@/layouts/ErrorLayout";
import configs from "@/configs";

const router = useRoute();

// Store Listen
const customizeTheme = configs.stores.useCustomizeThemeStore();

const isRouterLoaded = computed(() => {
  return router.name !== null;
});

const layouts = {
  default: DefaultLayout,
  communicate: CommunicationSidebarLayout,
  learn: LearnSidebarLayout,
  error: ErrorLayout
};

type LayoutName = "default" | "communicate" | "learn" | "error";

const currentLayout = computed(() => {
  const layoutName = router.meta.layout as LayoutName;
  if (layoutName) {
    return layouts[layoutName];
  } else {
    return DefaultLayout;
  }
});
</script>

<template>
  <v-app id="inspire" :theme="customizeTheme.darkTheme? 'dark' : 'light'">
    <component :is="currentLayout" v-if="isRouterLoaded">
      <router-view/>
    </component>
    <CustomizationMenu/>
  </v-app>
</template>

