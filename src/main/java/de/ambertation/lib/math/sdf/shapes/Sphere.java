package de.ambertation.lib.math.sdf.shapes;

import de.ambertation.lib.math.Bounds;
import de.ambertation.lib.math.Float3;
import de.ambertation.lib.math.sdf.SDF;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.KeyDispatchDataCodec;

public class Sphere extends BaseShape {
    public static final Codec<Sphere> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance
            .group(
                    Bounds.CODEC.fieldOf("bounds").forGetter(BaseShape::getBoundingBox)
            )
            .apply(instance, Sphere::new)
    );

    public static final KeyDispatchDataCodec<Sphere> CODEC = KeyDispatchDataCodec.of(DIRECT_CODEC);

    @Override
    public KeyDispatchDataCodec<? extends SDF> codec() {
        return CODEC;
    }


    //-------------------------------------------------------------------------------
    public Sphere(Bounds b) {
        super(b);
    }

    public Sphere(Float3 center, double radius) {
        super(Bounds.ofSphere(center, radius));
    }


    @Override
    public double dist(Float3 pos) {
        return pos.sub(getCenter()).length() - getRadius();
    }

    public double getRadius() {
        return bounds.minExtension() / 2 + 0.5;
    }
}