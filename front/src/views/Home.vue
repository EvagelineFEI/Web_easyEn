<script setup lang="ts">

</script>

<template>
  <div class="pa-5 align-center">
    <v-row class="flex-grow-0 mx-auto" dense>
      <v-sheet height="360" width="30%" elevation="4" rounded="lg" border>
        // usage
      </v-sheet>
      <v-spacer></v-spacer>
      <v-sheet height="360" width="30%" elevation="4" rounded="lg" border>
        // usage
      </v-sheet>
    </v-row>

    <v-container>
      <v-card class="card-shadow mx-auto" height="360" width="60%">
        // practice
      </v-card>
    </v-container>

    <v-row class="flex-grow-0" dense>
      <v-col cols="12" xl="6">
        <v-card class="card-shadow" height="360">
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<style lang="scss" scoped>
.card-shadow {
  box-shadow: rgba(99, 99, 99, 0.2) 0 2px 8px 0 !important;
}
</style>