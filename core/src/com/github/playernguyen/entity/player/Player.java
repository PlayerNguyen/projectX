package com.github.playernguyen.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.github.playernguyen.ProjectX;
import com.github.playernguyen.entity.Movable;

import java.util.HashMap;
import java.util.Map;

public class Player implements Movable {
    private final ProjectX game;
    private PlayerState state = PlayerState.WALKING;

    private final Map<PlayerState, Texture[]> textures = new HashMap<>();

    private Vector2 position;
    private Vector2 velocity;
    private Sprite sprite;

    private int currentFrame = 0;
    private long lastUpdateFrame = 0;
    private boolean reverse = false;

    public Player(ProjectX game, Vector2 position) {
        this.game = game;
        this.position = position;
        this.velocity = new Vector2(0, 0);
    }

    @Override
    public void load() {
        // Load texture
        registerTextures();

        // Load sprite with the current state
        sprite = new Sprite(textures.get(this.state)[0]);

        sprite.setSize(480 * 2, 480);
        sprite.setPosition(this.position.x, this.position.y);
    }

    private void registerTextures() {
        // Register idle texture with 12 assets
        Texture[] idleTextures = new Texture[12];
        for (int i = 0; i < 12; i++) {
            idleTextures[i] = new Texture(
                    Gdx
                            .files
                            .internal("sprites/golem/golem1/state/idle/" + (i + 1) + ".png")
            );
        }
        textures.put(PlayerState.IDLE, idleTextures);

        Texture[] walkingTextures = new Texture[18];
        for (int i = 0; i < 18; i++) {
            walkingTextures[i] = new Texture(
                    Gdx
                            .files
                            .internal("sprites/golem/golem1/state/walking/" + (i + 1) + ".png")
            );
        }
        textures.put(PlayerState.WALKING, walkingTextures);
    }

    @Override
    public void render() {
        // If the frame is out of frame, reset the current frame value to 0
        int totalFrameLength = textures.get(this.state).length - 1;

        if (!reverse && currentFrame >= totalFrameLength || reverse && currentFrame <= 0) {
            reverse = !reverse;
        }

        if (System.currentTimeMillis() - lastUpdateFrame > 30) {
            if (reverse) {
                currentFrame --;
            } else {
                currentFrame ++;
            }
            lastUpdateFrame = System.currentTimeMillis();
        }

        sprite.setTexture(textures.get(this.state)[currentFrame]);

        // Update location
        sprite.setPosition(this.position.x + this.velocity.x, this.position.y + this.velocity.y);

        System.out.println("Drawing player sprite ");
        game.getBatch().begin();
        sprite.draw(this.game.getBatch());
        game.getBatch().end();
    }

    @Override
    public void dispose() {
        // Clean up, dispose all <k, v>
        //                           ^ this value
        for (Map.Entry<PlayerState, Texture[]> playerStateEntry : textures.entrySet()) {
            for (Texture texture : playerStateEntry.getValue()) {
                texture.dispose();
            }
        }
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void setPosition(int x, int y) {
        this.position.x = x;
        this.position.y = y;
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    @Override
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    @Override
    public Vector2 getVelocity() {
        return velocity;
    }
}
