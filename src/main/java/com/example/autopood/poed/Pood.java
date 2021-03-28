package com.example.autopood.poed;

import com.example.autopood.models.Kuulutus;

import java.util.List;

public abstract class Pood {
    public abstract List<Kuulutus> refresh();
    public abstract void andmed();

}
