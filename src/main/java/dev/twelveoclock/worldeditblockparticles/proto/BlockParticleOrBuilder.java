// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: particles.proto

// Protobuf Java Version: 3.25.2
package dev.twelveoclock.worldeditblockparticles.proto;

public interface BlockParticleOrBuilder extends
    // @@protoc_insertion_point(interface_extends:proto.BlockParticle)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>uint64 blockPositionBitMask = 1;</code>
   * @return The blockPositionBitMask.
   */
  long getBlockPositionBitMask();

  /**
   * <code>.proto.Particle particle = 2;</code>
   * @return The enum numeric value on the wire for particle.
   */
  int getParticleValue();
  /**
   * <code>.proto.Particle particle = 2;</code>
   * @return The particle.
   */
  dev.twelveoclock.worldeditblockparticles.proto.Particle getParticle();

  /**
   * <code>uint32 count = 3;</code>
   * @return The count.
   */
  int getCount();
}