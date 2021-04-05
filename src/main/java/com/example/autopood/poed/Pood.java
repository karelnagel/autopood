package com.example.autopood.poed;

import com.example.autopood.models.Kuulutus;

import java.io.IOException;
import java.util.List;

public abstract class Pood {
    public abstract List<Kuulutus> refresh() throws IOException;
    public abstract void andmed();

}
