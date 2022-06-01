package com.example.flintlock.setup.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.world.entity.player.Player;

public class FlintlockHoldModel extends EntityModel<Player> {

    @Override
    public void setupAnim(Player p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
    }

    @Override
    public void renderToBuffer(PoseStack p_103111_, VertexConsumer p_103112_, int p_103113_, int p_103114_, float p_103115_, float p_103116_, float p_103117_, float p_103118_) {

    }
}
//package com.example.flintlock.setup.model;
//import com.example.flintlock.Flintlock;
//import com.example.flintlock.setup.ItemPro.FlintlockItem;
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.vertex.VertexConsumer;
//import net.minecraft.client.model.EntityModel;
//import net.minecraft.client.model.geom.ModelLayerLocation;
//import net.minecraft.client.model.geom.ModelPart;
//import net.minecraft.client.model.geom.PartPose;
//import net.minecraft.client.model.geom.builders.*;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.entity.Entity;

//public class FlintlockHoldModel<T extends Entity> extends EntityModel<T> {
//    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Flintlock.MODID, "tablet_hold"), "main");
//    private final ModelPart player;
//    private final ModelPart tablet;
//
//    public FlintlockHoldModel(ModelPart root) {
//        this.player = root.getChild("player");
//        this.tablet = root.getChild("flinklock");
//    }
//
//    public static LayerDefinition createBodyLayer() {
//        MeshDefinition meshdefinition = new MeshDefinition();
//        PartDefinition partdefinition = meshdefinition.getRoot();
//
//        PartDefinition player = partdefinition.addOrReplaceChild("player", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
//
//        PartDefinition Head = player.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
//                .texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -24.0F, 0.0F));
//
//        PartDefinition Body = player.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
//                .texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -24.0F, 0.0F));
//
//        PartDefinition RightArm = player.addOrReplaceChild("RightArm", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, -22.0F, 0.0F, -0.5901F, 0.4756F, 0.3902F));
//
//        PartDefinition upright = RightArm.addOrReplaceChild("upright", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
//                .texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));
//
//        PartDefinition downright = RightArm.addOrReplaceChild("downright", CubeListBuilder.create().texOffs(40, 22).addBox(-3.0F, 3.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
//                .texOffs(40, 38).addBox(-3.0F, 3.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.25F))
//                .texOffs(40, 16).addBox(-3.0F, 9.1F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 2.75F, -0.7854F, 0.0F, 0.0F));
//
//        PartDefinition LeftArm = player.addOrReplaceChild("LeftArm", CubeListBuilder.create(), PartPose.offsetAndRotation(5.0F, -22.0F, 0.0F, -0.8636F, 0.6925F, 0.4995F));
//
//        PartDefinition upleft = LeftArm.addOrReplaceChild("upleft", CubeListBuilder.create().texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.25F))
//                .texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1192F, 0.1232F, -0.3072F));
//
//        PartDefinition downleft = LeftArm.addOrReplaceChild("downleft", CubeListBuilder.create().texOffs(48, 54).addBox(-1.0F, 4.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.25F))
//                .texOffs(32, 54).addBox(-1.0F, 4.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
//                .texOffs(32, 48).addBox(-1.0F, 10.1F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.457F, -1.8633F, 0.2502F, -0.1048F, 0.1741F, 0.3049F));
//
//        PartDefinition RightLeg = player.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
//                .texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.9F, -12.0F, 0.0F));
//
//        PartDefinition LeftLeg = player.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
//                .texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(1.9F, -12.0F, 0.0F));
//
//        PartDefinition tablet = partdefinition.addOrReplaceChild("tablet", CubeListBuilder.create().texOffs(0, 0).addBox(-13.0F, -24.5F, -7.5F, 12.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
//                .texOffs(0, 13).addBox(-14.0F, -15.5F, -7.5F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
//                .texOffs(0, 10).addBox(-14.0F, -26.5F, -7.5F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
//                .texOffs(0, 18).addBox(-13.0F, -13.5F, -7.5F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
//                .texOffs(0, 16).addBox(-13.0F, -27.5F, -7.5F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.5F, 0.5F));
//
//        return LayerDefinition.create(meshdefinition, 64, 64);
//    }
//    @Override
//    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//
//    }
//
//    @Override
//    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
//        player.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//    }
//
//    public void renderToBuffer2(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
//        tablet.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//    }
//}
