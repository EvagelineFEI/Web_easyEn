<script lang="ts" setup>
import CustomizationMenu from "@/components/CustomizationMenu";
import DefaultLayout from "@/layouts/DefaultLayout";
import CommunicationSidebarLayout from "@/layouts/CommunicateSidebarLayout";
import PracticeSidebarLayout from "@/layouts/PracticeSidebarLayout.vue";
import ErrorLayout from "@/layouts/ErrorLayout";
import configs from "@/configs";

// Store Listen
const customizeTheme = configs.stores.useCustomizeThemeStore();

const router = useRoute();

const isRouterLoaded = computed(() => {
  return router.name !== null;
});

const layouts = {
  default: DefaultLayout,
  communicate: CommunicationSidebarLayout,
  learn: PracticeSidebarLayout,
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

<style>
.main-content {
  max-width: 1080px;
}
</style>

