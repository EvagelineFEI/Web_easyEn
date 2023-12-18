<script setup lang="ts">
const props = defineProps([
    'snackProvider',
    'snackColor',
    'snackProviderMessages'
]);

</script>

<template>
  <v-snackbar
      v-model="props.snackProvider"
      :timeout="2000"
      :color="props.snackColor">
    {{ props.snackProviderMessages }}
    <template v-slot:actions>
      <v-btn
          variant="text"
          @click="snackProvider = false">
        关闭
      </v-btn>
    </template>
  </v-snackbar>
</template>

<style scoped>

</style>