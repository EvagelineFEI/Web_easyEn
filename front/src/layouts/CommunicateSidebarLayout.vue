<script setup>
import MainAppBar from "@/components/MainAppBar";
import MainSideBar from "@/components/SideBar";
import configs from "@/configs";

const nav = ref(configs.menu.communicateNav);
</script>

<template>
  <MainAppBar/>
  <v-main class="view main-content w-full">
    <v-container>
      <v-row>
        <MainSideBar :list="nav"/>
        <v-col>
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

