<template>
  <div class="main">
    <div class="table">
      <div class="table-img">
        <div class="seats">
          <div class="upper">
            <SeatComponent card1="FIVE_C" card2="FIVE_C"></SeatComponent>
            <SeatComponent></SeatComponent>
            <SeatComponent></SeatComponent>
          </div>
          <div class="lower">
            <SeatComponent></SeatComponent>
            <SeatComponent></SeatComponent>
            <SeatComponent></SeatComponent>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SeatComponent from "@/components/Seat.vue";

export default {
  name: 'App',
  components: {
    SeatComponent
  }
}
</script>

<style>

html {
  min-height: 100%;
  background-color: lavender;
}

.table-img {
  background-image: url('~@/assets/table/table_1.png');
  background-size: cover;
  background-repeat: no-repeat;
  height: 361px;
  width: 769px;
}

.table {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 95vh;
  width: 95vh;
}

.main {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 100%;
}

.seats {
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  justify-content: space-between;
  height: 361px;
}

.upper {
  display: flex;
  width: 100%;
  justify-content: space-evenly;
}

.lower {
  display: flex;
  width: 100%;
  justify-content: space-evenly;
}
</style>
