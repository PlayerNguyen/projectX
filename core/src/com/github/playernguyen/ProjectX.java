package com.github.playernguyen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.playernguyen.entity.player.Player;

public class ProjectX extends ApplicationAdapter {
    // This one use for drawing matrix in game (drawer)
    private SpriteBatch batch;

    // Load background from texture
    private Texture backgroundTexture;
    private Sprite backgroundSprite;
    private OrthographicCamera camera;

    // Load player
    private Player player;

    @Override
    public void create() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture(Gdx.files.internal("backgrounds/bg2/bg2.png"));
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setPosition(0, 0);
        backgroundSprite.setSize(3840, 2160);

        float vw = 3840;
        float vh = 2160;

        camera = new OrthographicCamera(vw, vh);
        camera.translate(vw/2, vh/2);
        camera.update();

        player = new Player(this, new Vector2(0, vh/2));
        player.load();


    }

    @Override
    public void render() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // Render background
        batch.begin();
        backgroundSprite.draw(batch);
        batch.end();

        // Render player
        player.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
        player.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
