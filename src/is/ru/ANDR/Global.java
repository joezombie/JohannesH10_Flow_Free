package is.ru.ANDR;

import java.util.List;

/**
 * Created by joezombie on 19.9.2014.
 */
public class Global {
    private List<Pack> packs;

    private Global() {}

    private static Global singleInstance = new Global();

    public static Global getSingleInstance() { return singleInstance; }

    public List<Pack> getPacks() { return packs; }

    public void setPacks(List<Pack> packs) { this.packs = packs; }
}
