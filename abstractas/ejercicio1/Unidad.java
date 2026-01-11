package ejercicio1;

abstract class Unidad {
    protected int vida;
    protected int ataque;
    protected int defensa;

    public Unidad(int vida, int ataque, int defensa) {
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    public void atacar(Unidad objetivo) {
        int danio = Math.max(0, ataque - objetivo.defensa);
        objetivo.recibirDanio(danio);
    }

    public void recibirDanio(int danio) {
        vida -= danio;
        if (vida < 0) vida = 0;
    }

    public abstract void habilidadEspecial();
}

class SoldadoTerrestre extends Unidad {
    public SoldadoTerrestre() {
        super(100, 20, 10);
    }

    @Override
    public void habilidadEspecial() {
        System.out.println("Soldado usa granada (+10 ataque)");
        ataque += 10;
    }
}

class Tanque extends Unidad {
    public Tanque() {
        super(200, 40, 30);
    }

    @Override
    public void habilidadEspecial() {
        System.out.println("Tanque activa blindaje (+20 defensa)");
        defensa += 20;
    }
}

class Helicoptero extends Unidad {
    public Helicoptero() {
        super(150, 30, 15);
    }

    @Override
    public void habilidadEspecial() {
        System.out.println("Helicóptero dispara misiles (+15 ataque)");
        ataque += 15;
    }
}

class DragonVolador extends Unidad {
    public DragonVolador() {
        super(300, 50, 25);
    }

    @Override
    public void habilidadEspecial() {
        System.out.println("Dragón lanza fuego (+30 ataque)");
        ataque += 30;
    }
}


