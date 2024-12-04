package nazario.lesseroccultarts.library.geckolib;

import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;

public class AnimationInstance {
    protected String animation;
    protected ILoopType.EDefaultLoopTypes loopType;

    protected AnimationBuilder builder;

    public AnimationInstance() {
        this.builder = new AnimationBuilder();
    }

    public AnimationInstance setAnimation(String animation) {
        this.animation = animation;
        return this;
    }

    public AnimationInstance setLoopType(ILoopType.EDefaultLoopTypes loopType) {
        this.loopType = loopType;
        return this;
    }

    public AnimationBuilder play() {
        if(animation != null) {
            return builder.addAnimation(this.animation, this.loopType);
        } else {
            return builder;
        }
    }
}
