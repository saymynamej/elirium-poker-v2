<template>
  <div>
    <div class="seat">
      <div class="cards">
        <img class="card" src="{{ card }}" alt="">
        <img class="card" :src="card('2')" alt="">
      </div>
    </div>
  </div>
</template>

<script>


export default {
  name: 'Seat-Component',
  props: {
    card1: String,
    card2: String
  },
  components: {},
  methods: {
    card() {
      return '~@/assets/cards/FIVE_C.png'
    }
  }
}
</script>

<style>

.seat {
  background: gray;
  border: 0.1875em solid #0F1C3F;
  border-radius: 50%;
  box-shadow: 0.375em 0.375em 0 0 rgba(15, 28, 63, 0.125);
  height: 5em;
  width: 5em;
}

.cards {
  display: flex;
  align-items: center;
  justify-content: center;
}

.card {
  width: 72px;
  height: 72px;
}

</style>
