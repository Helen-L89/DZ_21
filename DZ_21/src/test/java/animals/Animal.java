package animals;

    public class Animal {
        private String name;
        private int age;
        private double weight;
        private String color;

        // Constructor
        public Animal(String name, int age, double weight, String color) {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.color = color;
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        // Methods
        public void say() {
            System.out.println("Я говорю");
        }

        public void go() {
            System.out.println("Я иду");
        }

        public void drink() {
            System.out.println("Я пью");
        }

        public void eat() {
            System.out.println("Я ем");
        }

        @Override
        public String toString() {
            String ageString = age == 1 ? "год" : (age >= 2 && age <= 4) ? "года" : "лет";
            return String.format("Привет! Меня зовут %s, мне %d %s, я вешу - %.1f кг, мой цвет - %s",
                    name, age, ageString, weight, color);
        }
    }


