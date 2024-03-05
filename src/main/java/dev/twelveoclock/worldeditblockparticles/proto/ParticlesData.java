// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: particles.proto

// Protobuf Java Version: 3.25.2
package dev.twelveoclock.worldeditblockparticles.proto;

public final class ParticlesData {
  private ParticlesData() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_BlockParticles_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_BlockParticles_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_UUID_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_UUID_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_BlockParticle_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_BlockParticle_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017particles.proto\022\005proto\"p\n\016BlockParticl" +
      "es\022\020\n\010tickRate\030\001 \001(\r\022\036\n\tworldUUID\030\002 \001(\0132" +
      "\013.proto.UUID\022,\n\016blockParticles\030\003 \003(\0132\024.p" +
      "roto.BlockParticle\"1\n\004UUID\022\023\n\013mostSigBit" +
      "s\030\001 \001(\004\022\024\n\014leastSigBits\030\002 \001(\004\"_\n\rBlockPa" +
      "rticle\022\034\n\024blockPositionBitMask\030\001 \001(\004\022!\n\010" +
      "particle\030\002 \001(\0162\017.proto.Particle\022\r\n\005count" +
      "\030\003 \001(\r*\203\026\n\010Particle\022\020\n\014PARTICLE_ASH\020\000\022\030\n" +
      "\024PARTICLE_BLOCK_CRACK\020\001\022\027\n\023PARTICLE_BLOC" +
      "K_DUST\020\002\022\031\n\025PARTICLE_BLOCK_MARKER\020\003\022\035\n\031P" +
      "ARTICLE_BUBBLE_COLUMN_UP\020\004\022\027\n\023PARTICLE_B" +
      "UBBLE_POP\020\005\022 \n\034PARTICLE_CAMPFIRE_COSY_SM" +
      "OKE\020\006\022\"\n\036PARTICLE_CAMPFIRE_SIGNAL_SMOKE\020" +
      "\007\022\032\n\026PARTICLE_CHERRY_LEAVES\020\010\022\022\n\016PARTICL" +
      "E_CLOUD\020\t\022\026\n\022PARTICLE_COMPOSTER\020\n\022\032\n\026PAR" +
      "TICLE_CRIMSON_SPORE\020\013\022\021\n\rPARTICLE_CRIT\020\014" +
      "\022\031\n\025PARTICLE_CURRENT_DOWN\020\r\022\035\n\031PARTICLE_" +
      "DAMAGE_INDICATOR\020\016\022\024\n\020PARTICLE_DOLPHIN\020\017" +
      "\022\032\n\026PARTICLE_DRAGON_BREATH\020\020\022\026\n\022PARTICLE" +
      "_DRIP_LAVA\020\021\022\027\n\023PARTICLE_DRIP_WATER\020\022\022$\n" +
      " PARTICLE_DRIPPING_DRIPSTONE_LAVA\020\023\022%\n!P" +
      "ARTICLE_DRIPPING_DRIPSTONE_WATER\020\024\022\033\n\027PA" +
      "RTICLE_DRIPPING_HONEY\020\025\022#\n\037PARTICLE_DRIP" +
      "PING_OBSIDIAN_TEAR\020\026\022\"\n\036PARTICLE_DUST_CO" +
      "LOR_TRANSITION\020\027\022\027\n\023PARTICLE_DUST_PLUME\020" +
      "\030\022\026\n\022PARTICLE_EGG_CRACK\020\031\022\033\n\027PARTICLE_EL" +
      "ECTRIC_SPARK\020\032\022\036\n\032PARTICLE_ENCHANTMENT_T" +
      "ABLE\020\033\022\024\n\020PARTICLE_END_ROD\020\034\022\033\n\027PARTICLE" +
      "_EXPLOSION_HUGE\020\035\022\034\n\030PARTICLE_EXPLOSION_" +
      "LARGE\020\036\022\035\n\031PARTICLE_EXPLOSION_NORMAL\020\037\022#" +
      "\n\037PARTICLE_FALLING_DRIPSTONE_LAVA\020 \022$\n P" +
      "ARTICLE_FALLING_DRIPSTONE_WATER\020!\022\031\n\025PAR" +
      "TICLE_FALLING_DUST\020\"\022\032\n\026PARTICLE_FALLING" +
      "_HONEY\020#\022\031\n\025PARTICLE_FALLING_LAVA\020$\022\033\n\027P" +
      "ARTICLE_FALLING_NECTAR\020%\022\"\n\036PARTICLE_FAL" +
      "LING_OBSIDIAN_TEAR\020&\022\"\n\036PARTICLE_FALLING" +
      "_SPORE_BLOSSOM\020\'\022\032\n\026PARTICLE_FALLING_WAT" +
      "ER\020(\022\034\n\030PARTICLE_FIREWORKS_SPARK\020)\022\022\n\016PA" +
      "RTICLE_FLAME\020*\022\022\n\016PARTICLE_FLASH\020+\022\021\n\rPA" +
      "RTICLE_GLOW\020,\022\033\n\027PARTICLE_GLOW_SQUID_INK" +
      "\020-\022\021\n\rPARTICLE_GUST\020.\022\026\n\022PARTICLE_GUST_D" +
      "UST\020/\022\031\n\025PARTICLE_GUST_EMITTER\0200\022\022\n\016PART" +
      "ICLE_HEART\0201\022\027\n\023PARTICLE_ITEM_CRACK\0202\022\032\n" +
      "\026PARTICLE_LANDING_HONEY\0203\022\031\n\025PARTICLE_LA" +
      "NDING_LAVA\0204\022\"\n\036PARTICLE_LANDING_OBSIDIA" +
      "N_TEAR\0205\022\021\n\rPARTICLE_LAVA\0206\022\037\n\033PARTICLE_" +
      "LEGACY_BLOCK_CRACK\0207\022\036\n\032PARTICLE_LEGACY_" +
      "BLOCK_DUST\0208\022 \n\034PARTICLE_LEGACY_FALLING_" +
      "DUST\0209\022\033\n\027PARTICLE_MOB_APPEARANCE\020:\022\025\n\021P" +
      "ARTICLE_NAUTILUS\020;\022\021\n\rPARTICLE_NOTE\020<\022\023\n" +
      "\017PARTICLE_PORTAL\020=\022\025\n\021PARTICLE_REDSTONE\020" +
      ">\022\033\n\027PARTICLE_REVERSE_PORTAL\020?\022\023\n\017PARTIC" +
      "LE_SCRAPE\020@\022\031\n\025PARTICLE_SCULK_CHARGE\020A\022\035" +
      "\n\031PARTICLE_SCULK_CHARGE_POP\020B\022\023\n\017PARTICL" +
      "E_SHRIEK\020C\022\022\n\016PARTICLE_SLIME\020D\022\030\n\024PARTIC" +
      "LE_SMALL_FLAME\020E\022\030\n\024PARTICLE_SMOKE_LARGE" +
      "\020F\022\031\n\025PARTICLE_SMOKE_NORMAL\020G\022\023\n\017PARTICL" +
      "E_NORMAL\020H\022\023\n\017PARTICLE_SNEEZE\020I\022\030\n\024PARTI" +
      "CLE_SNOW_SHOVEL\020J\022\025\n\021PARTICLE_SNOWBALL\020K" +
      "\022\027\n\023PARTICLE_SONICFLAKE\020L\022\021\n\rPARTICLE_SO" +
      "UL\020M\022\034\n\030PARTICLE_SOUL_FIRE_FLAME\020N\022\022\n\016PA" +
      "RTICLE_SPELL\020O\022\032\n\026PARTICLE_SPELL_INSTANT" +
      "\020P\022\026\n\022PARTICLE_SPELL_MOB\020Q\022\036\n\032PARTICLE_S" +
      "PELL_MOB_AMBIENT\020R\022\030\n\024PARTICLE_SPELL_WIT" +
      "CH\020S\022\021\n\rPARTICLE_SPIT\020T\022\036\n\032PARTICLE_SPOR" +
      "E_BLOSSOM_AIR\020U\022\026\n\022PARTICLE_SQUID_INK\020V\022" +
      "\026\n\022PARTICLE_SUSPENDED\020W\022\034\n\030PARTICLE_SUSP" +
      "ENDED_DEPTH\020X\022\031\n\025PARTICLE_SWEEP_ATTACK\020Y" +
      "\022\022\n\016PARTICLE_TOTEM\020Z\022\026\n\022PARTICLE_TOWN_AU" +
      "RA\020[\022$\n PARTICLE_TRIAL_SPAWNER_DETECTION" +
      "\020\\\022\026\n\022PARTICLE_VIBRATION\020]\022\033\n\027PARTICLE_V" +
      "ILLAGER_ANGRY\020^\022\033\n\027PARTICLE_VILLAGER_HAP" +
      "PY\020_\022\031\n\025PARTICLE_WARPED_SPORE\020`\022\031\n\025PARTI" +
      "CLE_WATER_BUBBLE\020a\022\027\n\023PARTICLE_WATER_DRO" +
      "P\020b\022\031\n\025PARTICLE_WATER_SPLASH\020c\022\027\n\023PARTIC" +
      "LE_WATER_WAKE\020d\022\024\n\020PARTICLE_WAX_OFF\020e\022\023\n" +
      "\017PARTICLE_WAX_ON\020f\022\026\n\022PARTICLE_WHITE_ASH" +
      "\020g\022\030\n\024PARTICLE_WHITE_SMOKE\020hBA\n.dev.twel" +
      "veoclock.worldeditblockparticles.protoB\r" +
      "ParticlesDataP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_proto_BlockParticles_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_BlockParticles_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_BlockParticles_descriptor,
        new java.lang.String[] { "TickRate", "WorldUUID", "BlockParticles", });
    internal_static_proto_UUID_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_proto_UUID_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_UUID_descriptor,
        new java.lang.String[] { "MostSigBits", "LeastSigBits", });
    internal_static_proto_BlockParticle_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_proto_BlockParticle_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_BlockParticle_descriptor,
        new java.lang.String[] { "BlockPositionBitMask", "Particle", "Count", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}