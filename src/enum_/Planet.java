package enum_;

public enum Planet {
    MERCURY(3.3011e23, 2.4397e6),
    VENUS(4.8675e24, 6.0518e6),
    EARTH(5.97237e24, 6.3710e6),
    MARS(6.4171e23, 3.3895e6),
    JUPITER(1.8982e27, 6.9911e7),
    SATURN(5.6834e26, 5.8232e7),
    URANUS(8.6810e25, 2.5362e7),
    NEPTUNE(1.02413e26, 2.4622e7);

    double mass;
    double radius;

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }

    public double getDensity() {
        double volume = (4 * Math.PI * Math.pow(this.radius, 3)) / 3;
        return this.mass / volume;
    }
}
