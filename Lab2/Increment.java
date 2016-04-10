package Lab2;

class Increment implements Runnable {

    PackedInteger packedInteger;
    Semafor binarySemaphore;
    int range;

    public Increment(Semafor binarySemaphore, PackedInteger packedInteger, int range) {
        this.binarySemaphore = binarySemaphore;
        this.packedInteger = packedInteger;
        this.range = range;
    }

    public void run() {
        for (int i = 0; i < range; i++) {
            binarySemaphore.P();
            packedInteger.increase();
            packedInteger.printValue();
            binarySemaphore.V();
        }
        packedInteger.printValue();
    }
}