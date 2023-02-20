package com.github.playernguyen.entity;

import com.badlogic.gdx.math.Vector2;

public interface Movable extends Entity {

    Vector2 getVelocity();

    void setVelocity(Vector2 velocity);
}
