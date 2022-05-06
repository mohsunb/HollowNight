package dev.pogodemon.display;

import java.awt.image.BufferedImage;

public class Assets
{
<<<<<<< HEAD
=======
    public static Font perpetua, trajan;

    public static BufferedImage[] soul1, soul2, soul3, soul4, soul5, soul6, soul7, soul8;
    public static BufferedImage[] downslash_left, downslash_right, upslash_left, upslash_right, slash2_left, slash2_right, slash1_right, slash1_left;
    public static BufferedImage[] walk_left, walk_right, slash1_char_left, slash1_char_right, slash2_char_left, slash2_char_right;
    public static BufferedImage[] upslash_char_right, upslash_char_left, downslash_char_left, downslash_char_right, low_hp_right, low_hp_left;
    public static BufferedImage[] dash_right, dash_left, shadow_dash_right, shadow_dash_left, shade_cloak_trail_left, shade_cloak_trail_right;
    public static BufferedImage[] wings_right, wings_left, shade_spawn_right, shade_spawn_left, shade_idle_right, shade_idle_left
            , shade_move_right, shade_move_left, shade_attack_left, shade_attack_right, shade_startle_right, shade_startle_left
            , shade_despawn_right, shade_despawn_left, shade_death_left, shade_death_right;
    public static BufferedImage[] superdash_charge_wind, superdash, superdash_trail;
    public static BufferedImage[] wall_hit, enemy_hit, spike_hit, geo_collect;

>>>>>>> 6aee207 (v0.3.6)
    public static BufferedImage player_right, player_left, tile_collision, tile_empty, grass, mask_full, mask_empty
            , soul_vessel_hud_up, soul_vessel_hud_down, dash_right, dash_left, walk_right, walk_left, jump_right, jump_left, fall_right, fall_left
            , fall_shock_right, fall_shock_left, cling_right, cling_left, slash1_right, slash1_left, slash2_right, slash2_left, upslash_right
            , upslash_left, downslash_right, downslash_left, upslash_char_right, upslash_char_left, downslash_char_right, downslash_char_left
            , slash1_char_left, slash1_char_right, slash2_char_right, slash2_char_left, spikes_small_up, spikes_small_right
            , spikes_small_down, spikes_small_left, spikes_medium_up, spikes_medium_right, spikes_medium_down, spikes_medium_left
            , spikes_large_up, spikes_large_down, spikes_large_left, spikes_large_right, damage_shock_right, damage_shock_left
<<<<<<< HEAD
            , wings_right, wings_left, superdash_right, superdash_left, superdash_charge_right, superdash_charge_left
=======
            , superdash_charge_right, superdash_charge_left
>>>>>>> 6aee207 (v0.3.6)
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
            , stalagmite, stalagmite_right, stalagmite_left, chest, chest_open_back, chest_open_front, soul_vessel_hud_middle, breakable_wall_generic_right
            , breakable_wall_generic_left, heal_right, heal_left, vengeful_spirit_right, vengeful_spirit_left, vengeful_spirit_big_right, vengeful_spirit_big_left
            , spell_char_right, spell_char_left, item_pickup_right, item_pickup_left, interact, mothwing_cloak, mantis_claw, crystal_heart, monarch_wings, shade_cloak
<<<<<<< HEAD
            , hallownest_seal, wanderers_journal, kings_idol, arcane_egg, damage, heal_decal, lifeblood_cocoon, lifeseed_right, lifeseed_left
            , lifeblood_mask;

    public static void init()
    {
=======
            , hallownest_seal, wanderers_journal, kings_idol, arcane_egg, damage_right, damage_left, heal_decal, lifeblood_cocoon, lifeseed_right, lifeseed_left
            , lifeblood_mask, bench_generic, bench_char_1, bench_char_2, cursor, icon, death_right_1, death_left_1, death_right_2, death_left_2, death_shock
            , vignette_low_hp, prop_nail, prop_shell, soul9, sleeping_right, sleeping_left, wakeup_right, wakeup_left;

    public static void init()
    {
        spike_hit = new BufferedImage[4];
        geo_collect = new BufferedImage[4];

        spike_hit[0] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/spike_hit_1.png");
        spike_hit[1] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/spike_hit_2.png");
        spike_hit[2] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/spike_hit_3.png");
        spike_hit[3] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/spike_hit_4.png");

        geo_collect[0] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/geo_collect_1.png");
        geo_collect[1] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/geo_collect_2.png");
        geo_collect[2] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/geo_collect_3.png");
        geo_collect[3] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/geo_collect_4.png");

        wall_hit = new BufferedImage[4];
        enemy_hit = new BufferedImage[5];

        wall_hit[0] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/wall_hit_1.png");
        wall_hit[1] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/wall_hit_2.png");
        wall_hit[2] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/wall_hit_3.png");
        wall_hit[3] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/wall_hit_4.png");

        enemy_hit[0] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/enemy_hit_1.png");
        enemy_hit[1] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/enemy_hit_2.png");
        enemy_hit[2] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/enemy_hit_3.png");
        enemy_hit[3] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/enemy_hit_4.png");
        enemy_hit[4] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/enemy_hit_5.png");

        superdash = new BufferedImage[4];
        superdash_trail = new BufferedImage[4];

        superdash[0] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/superdash_1.png");
        superdash[1] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/superdash_2.png");
        superdash[2] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/superdash_3.png");
        superdash[3] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/superdash_4.png");

        superdash_trail[0] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/superdash_trail/superdash_trail_1.png");
        superdash_trail[1] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/superdash_trail/superdash_trail_2.png");
        superdash_trail[2] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/superdash_trail/superdash_trail_3.png");
        superdash_trail[3] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/superdash_trail/superdash_trail_4.png");


        superdash_charge_wind = new BufferedImage[15];

        superdash_charge_wind[0] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/superdash_wind/wind_1.png");
        superdash_charge_wind[1] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/superdash_wind/wind_2.png");
        superdash_charge_wind[2] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/superdash_wind/wind_3.png");
        superdash_charge_wind[3] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/superdash_wind/wind_4.png");
        superdash_charge_wind[4] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/superdash_wind/wind_5.png");
        superdash_charge_wind[5] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/superdash_wind/wind_6.png");
        superdash_charge_wind[6] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/superdash_wind/wind_7.png");
        superdash_charge_wind[7] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/superdash_wind/wind_8.png");
        superdash_charge_wind[8] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/superdash_wind/wind_9.png");
        superdash_charge_wind[9] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/superdash_wind/wind_10.png");
        superdash_charge_wind[10] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/superdash_wind/wind_11.png");
        superdash_charge_wind[11] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/superdash_wind/wind_12.png");
        superdash_charge_wind[12] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/superdash_wind/wind_13.png");
        superdash_charge_wind[13] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/superdash_wind/wind_14.png");
        superdash_charge_wind[14] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/superdash_wind/wind_15.png");

        sleeping_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/sleeping_left.png");
        sleeping_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/sleeping_right.png");
        wakeup_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/wakeup_right.png");
        wakeup_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/wakeup_left.png");

        soul1 = new BufferedImage[6];
        soul2 = new BufferedImage[6];
        soul3 = new BufferedImage[6];
        soul4 = new BufferedImage[6];
        soul5 = new BufferedImage[6];
        soul6 = new BufferedImage[6];
        soul7 = new BufferedImage[6];
        soul8 = new BufferedImage[6];
        soul9 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/hud/soul/9.png");

        for (int i = 0; i < 6; i++)
        {
            soul1[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/hud/soul/1_" + (i + 1) + ".png");
            soul2[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/hud/soul/2_" + (i + 1) + ".png");
            soul3[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/hud/soul/3_" + (i + 1) + ".png");
            soul4[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/hud/soul/4_" + (i + 1) + ".png");
            soul5[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/hud/soul/5_" + (i + 1) + ".png");
            soul6[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/hud/soul/6_" + (i + 1) + ".png");
            soul7[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/hud/soul/7_" + (i + 1) + ".png");
            soul8[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/hud/soul/8_" + (i + 1) + ".png");
        }

        shade_death_left = new BufferedImage[75];
        shade_death_right = new BufferedImage[75];

        for (int i = 0; i < 31; i++)
        {
            shade_death_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_right_1.png");
            shade_death_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_left_1.png");

            if (i < 4)
            {
                shade_death_right[31 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_right_2.png");
                shade_death_left[31 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_left_2.png");
                shade_death_right[35 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_right_3.png");
                shade_death_left[35 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_left_3.png");
                shade_death_right[39 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_right_4.png");
                shade_death_left[39 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_left_4.png");
                shade_death_right[43 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_right_5.png");
                shade_death_left[43 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_left_5.png");
                shade_death_right[47 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_right_6.png");
                shade_death_left[47 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_left_6.png");
                shade_death_right[51 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_right_7.png");
                shade_death_left[51 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_left_7.png");
                shade_death_right[55 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_right_8.png");
                shade_death_left[55 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_left_8.png");
                shade_death_right[59 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_right_9.png");
                shade_death_left[59 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_left_9.png");
                shade_death_right[63 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_right_10.png");
                shade_death_left[63 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_left_10.png");
                shade_death_right[67 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_right_11.png");
                shade_death_left[67 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_left_11.png");
                shade_death_right[71 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_right_12.png");
                shade_death_left[71 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/death/shade_death_left_12.png");
            }
        }

        shade_despawn_left = new BufferedImage[35];
        shade_despawn_right = new BufferedImage[35];

        for (int i = 0; i < 5; i++)
        {
            if (i < 3)
            {
                shade_despawn_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/despawn/shade_despawn_right_1.png");
                shade_despawn_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/despawn/shade_despawn_left_1.png");
            }

            shade_despawn_right[3 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/despawn/shade_despawn_right_2.png");
            shade_despawn_left[3 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/despawn/shade_despawn_left_2.png");
            shade_despawn_right[8 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/despawn/shade_despawn_right_3.png");
            shade_despawn_left[8 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/despawn/shade_despawn_left_3.png");
            shade_despawn_right[13 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/despawn/shade_despawn_right_4.png");
            shade_despawn_left[13 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/despawn/shade_despawn_left_4.png");
            shade_despawn_right[18 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/despawn/shade_despawn_right_5.png");
            shade_despawn_left[18 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/despawn/shade_despawn_left_5.png");
            shade_despawn_right[23 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/despawn/shade_despawn_right_6.png");
            shade_despawn_left[23 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/despawn/shade_despawn_left_6.png");
            shade_despawn_right[28 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/despawn/shade_despawn_right_7.png");
            shade_despawn_left[28 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/despawn/shade_despawn_left_7.png");

            if (i < 2)
            {
                shade_despawn_right[33 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/despawn/shade_despawn_right_8.png");
                shade_despawn_left[33 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/despawn/shade_despawn_left_8.png");
            }
        }

        shade_attack_right = new BufferedImage[45];
        shade_attack_left = new BufferedImage[45];

        for (int i = 0; i < 5; i++)
        {
            shade_attack_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_right_1.png");
            shade_attack_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_left_1.png");
            shade_attack_right[5 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_right_2.png");
            shade_attack_left[5 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_left_2.png");
            if (i < 4)
            {
                shade_attack_right[10 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_right_3.png");
                shade_attack_left[10 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_left_3.png");
            }
            shade_attack_right[14 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_right_4.png");
            shade_attack_left[14 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_left_4.png");
            shade_attack_right[19 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_right_4.png");
            shade_attack_left[19 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_left_4.png");
            shade_attack_right[24 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_right_5.png");
            shade_attack_left[24 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_left_5.png");
            if (i < 3)
            {
                shade_attack_right[29 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_right_6.png");
                shade_attack_left[29 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_left_6.png");
                shade_attack_right[32 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_right_7.png");
                shade_attack_left[32 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_left_7.png");
            }
            shade_attack_right[35 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_right_8.png");
            shade_attack_left[35 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_left_8.png");
            shade_attack_right[40 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_right_9.png");
            shade_attack_left[40 + i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/attack/shade_attack_left_9.png");
        }

        shade_move_right = new BufferedImage[5];
        shade_move_left = new BufferedImage[5];

        shade_move_right[0] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/move/shade_move_right_1.png");
        shade_move_left[0] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/move/shade_move_left_1.png");
        shade_move_right[1] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/move/shade_move_right_2.png");
        shade_move_left[1] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/move/shade_move_left_2.png");
        shade_move_right[2] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/move/shade_move_right_3.png");
        shade_move_left[2] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/move/shade_move_left_3.png");
        shade_move_right[3] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/move/shade_move_right_4.png");
        shade_move_left[3] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/move/shade_move_left_4.png");
        shade_move_right[4] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/move/shade_move_right_5.png");
        shade_move_left[4] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/move/shade_move_left_5.png");

        shade_startle_left = new BufferedImage[7];
        shade_startle_right = new BufferedImage[7];

        shade_startle_right[0] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/startle/shade_startle_right_1.png");
        shade_startle_left[0] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/startle/shade_startle_left_1.png");
        shade_startle_right[1] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/startle/shade_startle_right_2.png");
        shade_startle_left[1] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/startle/shade_startle_left_2.png");
        shade_startle_right[2] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/startle/shade_startle_right_3.png");
        shade_startle_left[2] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/startle/shade_startle_left_3.png");
        shade_startle_right[3] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/startle/shade_startle_right_4.png");
        shade_startle_left[3] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/startle/shade_startle_left_4.png");
        shade_startle_right[4] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/startle/shade_startle_right_5.png");
        shade_startle_left[4] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/startle/shade_startle_left_5.png");
        shade_startle_right[5] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/startle/shade_startle_right_6.png");
        shade_startle_left[5] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/startle/shade_startle_left_6.png");
        shade_startle_right[6] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/startle/shade_startle_right_7.png");
        shade_startle_left[6] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/startle/shade_startle_left_7.png");

        shade_idle_right = new BufferedImage[6];
        shade_idle_left = new BufferedImage[6];

        shade_idle_right[0] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/idle/shade_idle_right_1.png");
        shade_idle_left[0] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/idle/shade_idle_left_1.png");
        shade_idle_right[1] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/idle/shade_idle_right_2.png");
        shade_idle_left[1] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/idle/shade_idle_left_2.png");
        shade_idle_right[2] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/idle/shade_idle_right_3.png");
        shade_idle_left[2] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/idle/shade_idle_left_3.png");
        shade_idle_right[3] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/idle/shade_idle_right_4.png");
        shade_idle_left[3] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/idle/shade_idle_left_4.png");
        shade_idle_right[4] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/idle/shade_idle_right_5.png");
        shade_idle_left[4] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/idle/shade_idle_left_5.png");
        shade_idle_right[5] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/idle/shade_idle_right_6.png");
        shade_idle_left[5] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/idle/shade_idle_left_6.png");

        shade_spawn_left = new BufferedImage[18];
        shade_spawn_right = new BufferedImage[18];

        shade_spawn_right[0] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_right_1.png");
        shade_spawn_left[0] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_left_1.png");
        shade_spawn_right[1] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_right_2.png");
        shade_spawn_left[1] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_left_2.png");
        shade_spawn_right[2] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_right_3.png");
        shade_spawn_left[2] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_left_3.png");
        shade_spawn_right[3] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_right_4.png");
        shade_spawn_left[3] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_left_4.png");
        shade_spawn_right[4] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_right_5.png");
        shade_spawn_left[4] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_left_5.png");
        shade_spawn_right[5] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_right_6.png");
        shade_spawn_left[5] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_left_6.png");
        shade_spawn_right[6] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_right_7.png");
        shade_spawn_left[6] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_left_7.png");
        shade_spawn_right[7] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_right_8.png");
        shade_spawn_left[7] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_left_8.png");
        shade_spawn_right[8] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_right_9.png");
        shade_spawn_left[8] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_left_9.png");
        shade_spawn_right[9] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_right_10.png");
        shade_spawn_left[9] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_left_10.png");
        shade_spawn_right[10] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_right_11.png");
        shade_spawn_left[10] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_left_11.png");
        shade_spawn_right[11] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_right_12.png");
        shade_spawn_left[11] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_left_12.png");
        shade_spawn_right[12] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_right_13.png");
        shade_spawn_left[12] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_left_13.png");
        shade_spawn_right[13] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_right_14.png");
        shade_spawn_left[13] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_left_14.png");
        shade_spawn_right[14] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_right_15.png");
        shade_spawn_left[14] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_left_15.png");
        shade_spawn_right[15] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_right_16.png");
        shade_spawn_left[15] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_left_16.png");
        shade_spawn_right[16] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_right_17.png");
        shade_spawn_left[16] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_left_17.png");
        shade_spawn_right[17] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_right_18.png");
        shade_spawn_left[17] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/shade/spawn/shade_spawn_left_18.png");

        prop_nail = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/player_death_nail_prop.png");
        prop_shell = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/player_death_shell_prop.png");

        wings_left = new BufferedImage[23];
        wings_right = new BufferedImage[23];

        for (int i = 0; i < 3; i++)
        {
            wings_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/wings_left_1.png");
            wings_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/wings_right_1.png");
        }

        for (int i = 3; i < 7; i++)
        {
            wings_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/wings_left_2.png");
            wings_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/wings_right_2.png");
        }

        for (int i = 7; i < 11; i++)
        {
            wings_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/wings_left_3.png");
            wings_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/wings_right_3.png");
        }

        for (int i = 11; i < 13; i++)
        {
            wings_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/wings_left_4.png");
            wings_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/wings_right_4.png");
        }

        wings_left[13] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/wings_left_5.png");
        wings_right[13] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/wings_right_5.png");

        for (int i = 14; i < 18; i++)
        {
            wings_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/wings_left_6.png");
            wings_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/wings_right_6.png");
        }

        for (int i = 18; i < 23; i++)
        {
            wings_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/wings_left_7.png");
            wings_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/wings_right_7.png");
        }

        shade_cloak_trail_left = new BufferedImage[20];
        shade_cloak_trail_right = new BufferedImage[20];

        for (int i = 0; i < 2; i++)
        {
            shade_cloak_trail_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/shade_cloak_trail/left_1.png");
            shade_cloak_trail_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/shade_cloak_trail/right_1.png");
        }

        for (int i = 2; i < 5; i++)
        {
            shade_cloak_trail_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/shade_cloak_trail/left_2.png");
            shade_cloak_trail_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/shade_cloak_trail/right_2.png");

            shade_cloak_trail_left[i + 3] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/shade_cloak_trail/left_3.png");
            shade_cloak_trail_right[i + 3] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/shade_cloak_trail/right_3.png");

            shade_cloak_trail_left[i + 6] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/shade_cloak_trail/left_4.png");
            shade_cloak_trail_right[i + 6] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/shade_cloak_trail/right_4.png");

            shade_cloak_trail_left[i + 9] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/shade_cloak_trail/left_5.png");
            shade_cloak_trail_right[i + 9] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/shade_cloak_trail/right_5.png");

            shade_cloak_trail_left[i + 12] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/shade_cloak_trail/left_6.png");
            shade_cloak_trail_right[i + 12] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/shade_cloak_trail/right_6.png");

            shade_cloak_trail_left[i + 15] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/shade_cloak_trail/left_7.png");
            shade_cloak_trail_right[i + 15] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/shade_cloak_trail/right_7.png");
        }

        shadow_dash_right = new BufferedImage[19];
        shadow_dash_left = new BufferedImage[19];

        for (int i = 0; i < 2; i++)
        {
            shadow_dash_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/shade_cloak_right_1.png");
            shadow_dash_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/shade_cloak_left_1.png");
        }

        for (int i = 2; i < 4; i++)
        {
            shadow_dash_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/shade_cloak_right_2.png");
            shadow_dash_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/shade_cloak_left_2.png");
        }

        for (int i = 4; i < 7; i++)
        {
            shadow_dash_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/shade_cloak_right_3.png");
            shadow_dash_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/shade_cloak_left_3.png");
        }

        for (int i = 7; i < 9; i++)
        {
            shadow_dash_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/shade_cloak_right_4.png");
            shadow_dash_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/shade_cloak_left_4.png");
        }

        for (int i = 9; i < 14; i++)
        {
            shadow_dash_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/shade_cloak_right_5.png");
            shadow_dash_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/shade_cloak_left_5.png");
        }

        for (int i = 14; i < 16; i++)
        {
            shadow_dash_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/shade_cloak_right_6.png");
            shadow_dash_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/shade_cloak_left_6.png");
        }

        for (int i = 16; i < 19; i++)
        {
            shadow_dash_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/shade_cloak_right_7.png");
            shadow_dash_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/shade_cloak_left_7.png");
        }

        dash_right = new BufferedImage[23];
        dash_left = new BufferedImage[23];

        for (int i = 0; i < 2; i++)
        {
            dash_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/dash_right_1.png");
            dash_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/dash_left_1.png");
        }

        for (int i = 2; i < 4; i++)
        {
            dash_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/dash_right_2.png");
            dash_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/dash_left_2.png");
        }

        for (int i = 4; i < 7; i++)
        {
            dash_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/dash_right_3.png");
            dash_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/dash_left_3.png");
        }

        for (int i = 7; i < 9; i++)
        {
            dash_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/dash_right_4.png");
            dash_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/dash_left_4.png");
        }

        for (int i = 9; i < 11; i++)
        {
            dash_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/dash_right_5.png");
            dash_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/dash_left_5.png");
        }

        for (int i = 11; i < 14; i++)
        {
            dash_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/dash_right_6.png");
            dash_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/dash_left_6.png");
        }

        for (int i = 14; i < 17; i++)
        {
            dash_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/dash_right_7.png");
            dash_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/dash_left_7.png");
        }

        for (int i = 17; i < 23; i++)
        {
            dash_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/dash_right_8.png");
            dash_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/dash_left_8.png");
        }

        low_hp_left = new BufferedImage[2];
        low_hp_right = new BufferedImage[2];

        low_hp_left[0] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/low_hp_left_1.png");
        low_hp_right[0] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/low_hp_right_1.png");
        low_hp_left[1] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/low_hp_left_2.png");
        low_hp_right[1] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/low_hp_right_2.png");

        vignette_low_hp = ImageLoader.loadImage("/dev/pogodemon/assets/textures/ui/vignette_low_health.png");

        death_right_1 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/death_right_1.png");
        death_right_2 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/death_right_2.png");
        death_left_1 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/death_left_1.png");
        death_left_2 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/death_left_2.png");

        death_shock = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/death_shock.png");

        cursor = ImageLoader.loadImage("/dev/pogodemon/assets/textures/ui/cursor.png");
        icon = ImageLoader.loadImage("/dev/pogodemon/assets/textures/ui/icon_256x256.png");

        bench_generic = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/bench/bench_generic.png");
        bench_char_1 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/bench_char_1.png");
        bench_char_2 = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/bench_char_2.png");

        downslash_char_right = new BufferedImage[20];
        downslash_char_left = new BufferedImage[20];

        for (int i = 0; i < 3; i++)
        {
            downslash_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/downslash_char_left_1.png");
            downslash_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/downslash_char_right_1.png");
        }

        for (int i = 3; i < 6; i++)
        {
            downslash_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/downslash_char_left_2.png");
            downslash_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/downslash_char_right_2.png");
        }

        for (int i = 6; i < 9; i++)
        {
            downslash_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/downslash_char_left_3.png");
            downslash_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/downslash_char_right_3.png");
        }

        for (int i = 9; i < 14; i++)
        {
            downslash_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/downslash_char_left_4.png");
            downslash_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/downslash_char_right_4.png");
        }

        for (int i = 14; i < 20; i++)
        {
            downslash_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/downslash_char_left_5.png");
            downslash_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/downslash_char_right_5.png");
        }

        upslash_char_left = new BufferedImage[21];
        upslash_char_right = new BufferedImage[21];

        for (int i = 0; i < 2; i++)
        {
            upslash_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/upslash_char_left_1.png");
            upslash_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/upslash_char_right_1.png");
        }

        for (int i = 2; i < 5; i++)
        {
            upslash_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/upslash_char_left_2.png");
            upslash_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/upslash_char_right_2.png");
        }

        for (int i = 5; i < 8; i++)
        {
            upslash_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/upslash_char_left_3.png");
            upslash_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/upslash_char_right_3.png");
        }

        for (int i = 8; i < 14; i++)
        {
            upslash_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/upslash_char_left_4.png");
            upslash_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/upslash_char_right_4.png");
        }

        for (int i = 14; i < 21; i++)
        {
            upslash_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/upslash_char_left_5.png");
            upslash_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/upslash_char_right_5.png");
        }

        walk_left = new BufferedImage[4];
        walk_right = new BufferedImage[4];

        walk_left[0] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/walk_left_1.png");
        walk_right[0] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/walk_right_1.png");
        walk_left[1] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/walk_left_2.png");
        walk_right[1] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/walk_right_2.png");
        walk_left[2] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/walk_left_3.png");
        walk_right[2] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/walk_right_3.png");
        walk_left[3] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/walk_left_4.png");
        walk_right[3] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/walk_right_4.png");

        slash1_char_left = new BufferedImage[21];
        slash1_char_right = new BufferedImage[21];
        slash2_char_left = new BufferedImage[21];
        slash2_char_right = new BufferedImage[21];

        for (int i = 0; i < 3; i++)
        {
            slash1_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash1_char_left_1.png");
            slash2_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash2_char_left_1.png");
            slash1_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash1_char_right_1.png");
            slash2_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash2_char_right_1.png");
            upslash_char_left[i + 5] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/upslash_char_left_3.png");
            upslash_char_right[i + 5] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/upslash_char_right_3.png");
        }

        for (int i = 3; i < 6; i++)
        {
            slash1_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash1_char_left_2.png");
            slash2_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash2_char_left_2.png");
            slash1_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash1_char_right_2.png");
            slash2_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash2_char_right_2.png");
        }

        for (int i = 6; i < 8; i++)
        {
            slash1_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash1_char_left_3.png");
            slash2_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash2_char_left_3.png");
            slash1_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash1_char_right_3.png");
            slash2_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash2_char_right_3.png");
        }

        for (int i = 8; i < 14; i++)
        {
            slash1_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash1_char_left_4.png");
            slash2_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash2_char_left_4.png");
            slash1_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash1_char_right_4.png");
            slash2_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash2_char_right_4.png");
        }

        for (int i = 14; i < 21; i++)
        {
            slash1_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash1_char_left_5.png");
            slash2_char_left[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash2_char_left_5.png");
            slash1_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash1_char_right_5.png");
            slash2_char_right[i] = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/model/slash2_char_right_5.png");
        }

>>>>>>> 6aee207 (v0.3.6)
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
        chest_open_back = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/chest/chest_open_back.png");
        chest_open_front = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/chest/chest_open_front.png");
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
        damage_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/damage_shock_right.png");
        damage_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/damage_shock_left.png");
        heal_decal = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/decals/heal_decal.png");
        lifeblood_cocoon = ImageLoader.loadImage("/dev/pogodemon/assets/textures/objects/environment/lifeblood_cocoon.png");
        lifeblood_mask = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player/hud/lifeblood_mask.png");
        lifeseed_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/lifeseed/lifeseed_right.png");
        lifeseed_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/creatures/lifeseed/lifeseed_left.png");
    }
}
