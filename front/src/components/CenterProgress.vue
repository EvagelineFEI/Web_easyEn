<script setup lang="ts">

</script>

<template>
<v-container>
    <v-row
        class="h-full"
        align-content="center"
        justify="center"
    >
        <v-col
            align-self="center"
            class="text-subtitle-1 text-center h-full"
            cols="12"
        >
            等待返回结果
        </v-col>
        <v-col cols="6">
            <v-progress-linear
                color="primary"
                indeterminate
                rounded
                height="6"
            ></v-progress-linear>
        </v-col>
    </v-row>
</v-container>
</template>

<style scoped>

</style>