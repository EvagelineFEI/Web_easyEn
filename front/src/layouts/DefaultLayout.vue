<template>
  <MainAppBar/>
  <v-main class="view align-self-center main-content w-full">
    <v-container class="align-self-auto">
      <slot/>
    </v-container>
  </v-main>
</template>

<script setup>
import MainAppBar from "@/components/MainAppBar";
</script>