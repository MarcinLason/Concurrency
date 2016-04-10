package Lab2;

class Race {
    public static void main(String[] args) {
        Semafor binarySemaphore = new Semafor(true);
        PackedInteger packedInteger = new PackedInteger(0);
        int range = 1000;
        Increment increment = new Increment(binarySemaphore, packedInteger, range);
        Decrement decrement = new Decrement(binarySemaphore, packedInteger, range);

        Thread incrementThread = new Thread(increment);
        Thread decrementThread = new Thread(decrement);
        incrementThread.start();
        decrementThread.start();
    }
}