interface Shape{
    double calculateArea();
}

class Circle implements Shape{
    private double radius;

    public Circle(double radius){
        this.radius=radius;
    }

    public double calculateArea(){
        return Math.PI*radius*radius;
    }

    public void display(){
        System.out.println("Circle: Radius = "+radius);
        System.out.println("Area of Circle = "+calculateArea());
    }
}

class Rectangle implements Shape{
    private double length;
    private double width;

    public Rectangle(double length,double width){
        this.length=length;
        this.width=width;
    }

    public double calculateArea(){
        return length*width;
    }

    public void display(){
        System.out.println("Rectangle: Length = "+length+", Width = "+width);
        System.out.println("Area of Rectangle = "+calculateArea());
    }
}

class Triangle implements Shape{
    protected double base;
    protected double height;

    public Triangle(double base,double height){
        this.base=base;
        this.height=height;
    }

    public double calculateArea(){
        return 0.5*base*height;
    }

    public void display(){
        System.out.println("Triangle: Base = "+base+", Height = "+height);
        System.out.println("Area of Triangle = "+calculateArea());
    }
}

public class first{
    public static void main(String[] args){
        Circle c=new Circle(5.0);
        Rectangle r=new Rectangle(4.0,6.0);
        Triangle t=new Triangle(3.0,8.0);

        System.out.println("----- Shape Area Calculator -----");
        c.display();
        r.display();
        t.display();
    }
}

