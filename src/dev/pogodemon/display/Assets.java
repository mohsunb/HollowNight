package dev.pogodemon.display;

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class Assets
{
    public static BufferedImage player_right, player_left, tile_collision, tile_empty, grass, mask_full, mask_empty
            , soul_vessel_hud_up, soul_vessel_hud_down, dash_right, dash_left, walk_right, walk_left, jump_right, jump_left, fall_right, fall_left, fall_shock_right
            , fall_shock_left, cling_right, cling_left, slash1_right, slash1_left, slash2_right, slash2_left, upslash_right, upslash_left
            , downslash_right, downslash_left, upslash_char_right, upslash_char_left, downslash_char_right, downslash_char_left
            , slash1_char_left, slash1_char_right, slash2_char_right, slash2_char_left, spikes_small_up, spikes_small_right
            , spikes_small_down, spikes_small_left, spikes_medium_up, spikes_medium_right, spikes_medium_down, spikes_medium_left
            , spikes_large_up, spikes_large_down, spikes_large_left, spikes_large_right, damage_shock_right, damage_shock_left
            , wings_right, wings_left, superdash_right, superdash_left, superdash_charge_right, superdash_charge_left
            , superdash_charge_wall_left, superdash_charge_wall_right, superdash_shocked_right, superdash_shocked_left

            , superdash_crystals_right_1, superdash_crystals_right_2, superdash_crystals_right_3, superdash_crystals_right_4
            , superdash_crystals_left_1, superdash_crystals_left_2, superdash_crystals_left_3, superdash_crystals_left_4
            , superdash_crystals_wall_right_1, superdash_crystals_wall_right_2, superdash_crystals_wall_right_3, superdash_crystals_wall_right_4
            , superdash_crystals_wall_left_1, superdash_crystals_wall_left_2, superdash_crystals_wall_left_3, superdash_crystals_wall_left_4

            , shadow_dash_right, shadow_dash_left, wandering_husk_idle_right
            , wandering_husk_idle_left, wandering_husk_walk_right, wandering_husk_walk_left, wandering_husk_attack_right
            , wandering_husk_attack_left, wandering_husk_dead_right, wandering_husk_dead_left, crawlid_right, crawlid_left, crawlid_dead_right
            , crawlid_dead_left, geo_hud, geo_small, geo_medium, geo_large, geo_deposit_up, geo_deposit_down, geo_deposit_left, geo_deposit_right
            , geo_deposit_broken_up, geo_deposit_broken_down, geo_deposit_broken_left, geo_deposit_broken_right, vengefly_idle_right
            , vengefly_idle_left, vengefly_agro_right, vengefly_agro_left, player_up_right, player_up_left, player_down_right, player_down_left
            , stalagmite, stalagmite_right, stalagmite_left, chest, chest_open, soul_vessel_hud_middle, breakable_wall_generic_right, breakable_wall_generic_left
            , heal_right, heal_left, vengeful_spirit_right, vengeful_spirit_left, vengeful_spirit_big_right, vengeful_spirit_big_left, spell_char_right
            , spell_char_left, item_pickup_right, item_pickup_left, interact, mothwing_cloak, mantis_claw, crystal_heart, monarch_wings, shade_cloak
            , hallownest_seal, wanderers_journal, kings_idol, arcane_egg, damage;

    public static void init()
    {
        vengefly_idle_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/vengefly/vengefly_idle_right.png");
        vengefly_idle_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/vengefly/vengefly_idle_left.png");
        vengefly_agro_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/vengefly/vengefly_agro_right.png");
        vengefly_agro_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/vengefly/vengefly_agro_left.png");
        geo_deposit_up = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/geo/geo_deposit_up.png");
        geo_deposit_down = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/geo/geo_deposit_down.png");
        geo_deposit_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/geo/geo_deposit_left.png");
        geo_deposit_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/geo/geo_deposit_right.png");
        geo_deposit_broken_up = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/geo/geo_deposit_broken_up.png");
        geo_deposit_broken_down = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/geo/geo_deposit_broken_down.png");
        geo_deposit_broken_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/geo/geo_deposit_broken_left.png");
        geo_deposit_broken_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/geo/geo_deposit_broken_right.png");
        geo_small = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/geo/geo_small.png");
        geo_medium = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/geo/geo_medium.png");
        geo_large = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/geo/geo_large.png");
        geo_hud = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/hud/geo_hud.png");
        crawlid_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/crawlid/crawlid_right.png");
        crawlid_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/crawlid/crawlid_left.png");
        crawlid_dead_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/crawlid/crawlid_dead_right.png");
        crawlid_dead_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/crawlid/crawlid_dead_left.png");
        wandering_husk_idle_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/wandering_husk/husk_idle_right.png");
        wandering_husk_idle_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/wandering_husk/husk_idle_left.png");
        wandering_husk_walk_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/wandering_husk/husk_walk_right.png");
        wandering_husk_walk_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/wandering_husk/husk_walk_left.png");
        wandering_husk_attack_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/wandering_husk/husk_attack_right.png");
        wandering_husk_attack_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/wandering_husk/husk_attack_left.png");
        wandering_husk_dead_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/wandering_husk/husk_dead_right.png");
        wandering_husk_dead_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/wandering_husk/husk_dead_left.png");
        shadow_dash_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/shadow_dash_right.png");
        shadow_dash_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/shadow_dash_left.png");

        superdash_crystals_wall_right_1 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/right/wall/superdash_crystals_wall_right_1.png");
        superdash_crystals_wall_right_2 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/right/wall/superdash_crystals_wall_right_2.png");
        superdash_crystals_wall_right_3 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/right/wall/superdash_crystals_wall_right_3.png");
        superdash_crystals_wall_right_4 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/right/wall/superdash_crystals_wall_right_4.png");

        superdash_crystals_wall_left_1 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/left/wall/superdash_crystals_wall_left_1.png");
        superdash_crystals_wall_left_2 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/left/wall/superdash_crystals_wall_left_2.png");
        superdash_crystals_wall_left_3 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/left/wall/superdash_crystals_wall_left_3.png");
        superdash_crystals_wall_left_4 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/left/wall/superdash_crystals_wall_left_4.png");

        superdash_crystals_right_1 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/right/superdash_crystals_right_1.png");
        superdash_crystals_right_2 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/right/superdash_crystals_right_2.png");
        superdash_crystals_right_3 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/right/superdash_crystals_right_3.png");
        superdash_crystals_right_4 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/right/superdash_crystals_right_4.png");

        superdash_crystals_left_1 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/left/superdash_crystals_left_1.png");
        superdash_crystals_left_2 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/left/superdash_crystals_left_2.png");
        superdash_crystals_left_3 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/left/superdash_crystals_left_3.png");
        superdash_crystals_left_4 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/left/superdash_crystals_left_4.png");

        superdash_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/superdash_right.png");
        superdash_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/superdash_left.png");
        superdash_charge_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/superdash_charge_right.png");
        superdash_charge_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/superdash_charge_left.png");
        superdash_charge_wall_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/superdash_charge_wall_left.png");
        superdash_charge_wall_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/superdash_charge_wall_right.png");
        superdash_shocked_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/superdash_shocked_right.png");
        superdash_shocked_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/superdash_shocked_left.png");
        player_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/player_right.png");
        player_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/player_left.png");
        tile_collision = ImageLoader.loadImage("/dev/pogodemon/assets/textures/tile_collision.png");
        tile_empty = ImageLoader.loadImage("/dev/pogodemon/assets/textures/tile_empty.png");
        grass = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/environment/grass.png");
        mask_full = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/hud/mask_full.png");
        mask_empty = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/hud/mask_empty.png");
        soul_vessel_hud_up = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/hud/soul_vessel_hud_up.png");
        soul_vessel_hud_down = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/hud/soul_vessel_hud_down.png");
        dash_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/dash_right.png");
        dash_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/dash_left.png");
        walk_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/walk_right.png");
        walk_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/walk_left.png");
        jump_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/jump_right.png");
        jump_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/jump_left.png");
        fall_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/fall_right.png");
        fall_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/fall_left.png");
        fall_shock_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/fall_shock_right.png");
        fall_shock_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/fall_shock_left.png");
        cling_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/cling_right.png");
        cling_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/cling_left.png");
        slash1_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/nail_slash/slash1_right.png");
        slash1_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/nail_slash/slash1_left.png");
        slash2_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/nail_slash/slash2_right.png");
        slash2_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/nail_slash/slash2_left.png");
        upslash_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/nail_slash/upslash_right.png");
        upslash_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/nail_slash/upslash_left.png");
        downslash_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/nail_slash/downslash_right.png");
        downslash_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/nail_slash/downslash_left.png");
        upslash_char_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/upslash_char_right.png");
        upslash_char_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/upslash_char_left.png");
        downslash_char_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/downslash_char_right.png");
        downslash_char_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/downslash_char_left.png");
        slash1_char_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash1_char_left.png");
        slash1_char_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash1_char_right.png");
        slash2_char_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash2_char_right.png");
        slash2_char_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash2_char_left.png");
        spikes_small_up = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/spikes/spikes_small_up.png");
        spikes_small_down = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/spikes/spikes_small_down.png");
        spikes_small_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/spikes/spikes_small_left.png");
        spikes_small_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/spikes/spikes_small_right.png");
        spikes_medium_up = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/spikes/spikes_medium_up.png");
        spikes_medium_down = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/spikes/spikes_medium_down.png");
        spikes_medium_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/spikes/spikes_medium_left.png");
        spikes_medium_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/spikes/spikes_medium_right.png");
        spikes_large_up = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/spikes/spikes_large_up.png");
        spikes_large_down = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/spikes/spikes_large_down.png");
        spikes_large_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/spikes/spikes_large_left.png");
        spikes_large_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/spikes/spikes_large_right.png");
        damage_shock_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/damage_shock_right.png");
        damage_shock_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/damage_shock_left.png");
        wings_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/wings_right.png");
        wings_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/wings_left.png");
        player_up_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/player_up_right.png");
        player_up_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/player_up_left.png");
        player_down_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/player_down_right.png");
        player_down_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/player_down_left.png");
        stalagmite = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/environment/stalagmite/stalagmite.png");
        stalagmite_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/environment/stalagmite/stalagmite_right.png");
        stalagmite_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/environment/stalagmite/stalagmite_left.png");
        chest = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/chest/chest.png");
        chest_open = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/chest/chest_open.png");
        soul_vessel_hud_middle = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/hud/soul_vessel_hud_middle.png");
        breakable_wall_generic_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/environment/breakable_wall_generic_right.png");
        breakable_wall_generic_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/environment/breakable_wall_generic_left.png");
        heal_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/heal_right.png");
        heal_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/heal_left.png");
        vengeful_spirit_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/spells/vengeful_spirit_right.png");
        vengeful_spirit_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/spells/vengeful_spirit_left.png");
        vengeful_spirit_big_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/spells/vengeful_spirit_big_right.png");
        vengeful_spirit_big_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/spells/vengeful_spirit_big_left.png");
        spell_char_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/spell_char_right.png");
        spell_char_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/spell_char_left.png");
        item_pickup_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/item_pickup_right.png");
        item_pickup_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/item_pickup_left.png");
        interact = ImageLoader.loadImage("/dev/pogodemon/assets/textures/ui/interact.png");
        mothwing_cloak = ImageLoader.loadImage("/dev/pogodemon/assets/textures/ui/items/mothwing_cloak.png");
        mantis_claw = ImageLoader.loadImage("/dev/pogodemon/assets/textures/ui/items/mantis_claw.png");
        crystal_heart = ImageLoader.loadImage("/dev/pogodemon/assets/textures/ui/items/crystal_heart.png");
        monarch_wings = ImageLoader.loadImage("/dev/pogodemon/assets/textures/ui/items/monarch_wings.png");
        shade_cloak = ImageLoader.loadImage("/dev/pogodemon/assets/textures/ui/items/shade_cloak.png");
        hallownest_seal = ImageLoader.loadImage("/dev/pogodemon/assets/textures/ui/items/hallownest_seal.png");
        wanderers_journal = ImageLoader.loadImage("/dev/pogodemon/assets/textures/ui/items/wanderers_journal.png");
        kings_idol = ImageLoader.loadImage("/dev/pogodemon/assets/textures/ui/items/kings_idol.png");
        arcane_egg = ImageLoader.loadImage("/dev/pogodemon/assets/textures/ui/items/arcane_egg.png");
        damage = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/damage_shock.png");
    }
}
