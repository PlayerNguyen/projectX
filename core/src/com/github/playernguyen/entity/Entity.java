package com.github.playernguyen.entity;

import com.badlogic.gdx.math.Vector2;
import com.github.playernguyen.GameObject;

public interface Entity extends GameObject {

    /**
     * a position of the entity
     * @return a current position as vector2
     */
    Vector2 getPosition();

    /**
     * Set the current position of the entity
     * @param position the position as vector2
     */
    void setPosition(Vector2 position);

    /**
     * Set the current position of the entity
     * @param x the position x coordinate value
     * @param y the position y coordinate value
     */
    void setPosition(int x, int y);
}
