package Lab2;

class PackedInteger {
    int value;

    PackedInteger(int value) {
        this.value = value;
    }

    public void increase() {
        this.value = this.value + 1;
    }

    public void decrease() {
        this.value = this.value - 1;
    }

    public void printValue() {
        System.out.println(this.value);
    }
}