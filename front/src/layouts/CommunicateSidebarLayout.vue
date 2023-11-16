<script setup>
import MainAppBar from "@/components/MainAppBar";
import configs from "@/configs";

const nav = ref(configs.menu.communicateNav);
</script>

<template>
  <MainAppBar/>
  <v-main class="view align-self-center main-content w-75">
    <v-container>
      <v-row>
        <v-col>
          <slot/>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

