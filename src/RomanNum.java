enum RomanNum {
    C(100), XC(90), L(50), XL(40), X(10), IX(9), V(5), IV(4), I(1);


    private int i;

    RomanNum(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
