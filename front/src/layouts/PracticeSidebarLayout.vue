<script setup lang="ts">
import MainAppBar from "@/components/MainAppBar.vue";
import MainSideBar from "@/components/SideBar.vue";
import configs from "@/configs";

const nav = ref(configs.menu.learnNav);
</script>

<template>
  <MainAppBar/>
  <v-main class="view align-self-center main-content w-75">
    <v-container class="align-self-auto">
      <v-row>
        <v-col cols="3">
          <v-card>
            <MainSideBar :list="nav"/>
          </v-card>
        </v-col>
        <v-col cols="9">
          <v-sheet
              min-height="70vh"
              elevation="4"
              rounded="lg"
              border
          >
            <slot/>
          </v-sheet>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

