<template>
  <MainAppBar/>
  <v-main class="view main-content w-full" >
    <v-container class="justify-betweenflex-wrap">
      <slot> </slot>
    </v-container>
  </v-main>
</template>

<script setup>
import MainAppBar from "@/components/MainAppBar";
</script>