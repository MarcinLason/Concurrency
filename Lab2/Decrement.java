package Lab2;

class Decrement implements Runnable {

    PackedInteger packedInteger;
    Semafor binarySemaphore;
    int range;

    public Decrement(Semafor binarySemaphore, PackedInteger packedInteger, int range) {
        this.binarySemaphore = binarySemaphore;
        this.packedInteger = packedInteger;
        this.range = range;
    }

    public void run() {
        for (int i = 0; i < range; i++) {
            binarySemaphore.P();
            packedInteger.decrease();
            packedInteger.printValue();
            binarySemaphore.V();
        }
        System.out.println(packedInteger.value);
    }
}