<script setup lang="ts">
import configs from "@/configs";

const customizeTheme = configs.stores.useCustomizeThemeStore();

const props = defineProps({
  list: {
    type: Array<any>,
    default: () => [],
  },
});
</script>

<template>
  <v-list class="menu-list" nav dense color="primary">
    <template v-for="listArea in props.list">
      <template v-if="!listArea.link && listArea.items" :key="listArea.key">
        <div
            v-if="!customizeTheme.miniSidebar && (listArea.key || listArea.text)"
            class="pa-1 mt-2 text-overline"
        >
          <v-icon
              class="me-2"
              :active-class="`active-nav-${customizeTheme.primaryColor.colorName}`"
              :icon="listArea.icon || 'mdi-circle-medium'"
          />
          {{ listArea.key ? $t(listArea.key) : listArea.text }}
        </div>
        <template v-if="listArea.items">
          <template v-for="listItem in listArea.items" :key="listItem.key">
            <v-list-item
                class="me-2"
                :to="listItem.link"
                :prepend-icon="listItem.icon || 'mdi-circle-medium'"
                :active-class="`active-nav-${customizeTheme.primaryColor.colorName}`"
                density="compact"
            >
              <v-list-item-title
                  v-text="listItem.key ? $t(listItem.key) : listItem.text"
              ></v-list-item-title>
            </v-list-item>
          </template>
        </template>
        <hr class="my-4"/>
      </template>
      <template v-else>
        <v-list-item
            class="me-2"
            :key="listArea.key"
            :to="listArea.link"
            :prepend-icon="listArea.icon || 'mdi-circle-medium'"
            :active-class="`active-nav-${customizeTheme.primaryColor.colorName}`"
            density="compact"
        >
          <v-list-item-title
              class="text-"
              v-text="listArea.key ? $t(listArea.key) : listArea.text"
          ></v-list-item-title>
        </v-list-item>
      </template>
    </template>
  </v-list>
</template>

<style scoped>
.v-list-group .v-list-item {
  padding-left: 8px !important;
}

.active-nav-grey {
  border-left: 5px solid;
  border-image-slice: 1;
  border-image-source: linear-gradient(to bottom, #3a456c, #a4abbb);
}

.active-nav-purple {
  border-left: 5px solid;
  border-image-slice: 1;
  border-image-source: linear-gradient(to bottom, #e82893, #954bcb);
}

.active-nav-info {
  border-left: 5px solid;
  border-image-slice: 1;
  border-image-source: linear-gradient(to bottom, #487afa, #3fc7f3);
}

.active-nav-success {
  border-left: 5px solid;
  border-image-slice: 1;
  border-image-source: linear-gradient(to bottom, #45b95b, #96dd4c);
}

.active-nav-warning {
  border-left: 5px solid;
  border-image-slice: 1;
  border-image-source: linear-gradient(to bottom, #f0635d, #edc252);
}

.active-nav-error {
  border-left: 5px solid;
  border-image-slice: 1;
  border-image-source: linear-gradient(to bottom, #e75b5d, #ffd1d4);
}

hr {
  background-image: linear-gradient(
      90deg,
      transparent,
      rgba(0, 0, 0, 0.4),
      transparent
  ) !important;
  background-color: transparent;
  opacity: 0.25;
  border: none;
  height: 1px;
}
</style>
