public class Point3d extends Point2d {
    private double zCoord; //Координата Z

    public Point3d(double x, double y, double z){
        super(x, y);
        zCoord = z;
    }
    public Point3d(){
        this(0, 0, 0);
    }

    public double distanceTo(Point3d p){
        double xx = this.getX() - p.getX();
        double yy = this.getY() - p.getY();
        double zz = this.getZ() - p.getZ();
        return (Math.sqrt(xx*xx + yy*yy + zz*zz));
    }

    public double getZ() { //возврат координаты Z
        return zCoord;
    }

    public void setZ(double val){ //возвращение координаты Z
        zCoord = val;
    }

}
