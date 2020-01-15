package com.dankegongyu.basic.libs.utils;

import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;

import com.dankegongyu.basic.libs.module.DKGlobalContext;

import java.io.IOException;

/**
 * 描述：播放声音帮助类
 * 作者：ChaoZheng on 2020-01-14 22:54
 * 邮箱：chaozheng@dankegongyu.com
 */
public class DkSoundUtils {
    /**
     * 播放声音 不能同时播放多种音频
     * 消耗资源较大
     *
     * @param rawId
     */
    public static void playSoundByMedia(int rawId) {
        playSoundByMedia(rawId, 0.5f, 0.5f);
    }

    /**
     * 播放声音 不能同时播放多种音频
     * 消耗资源较大
     *
     * @param rawId
     */
    public static void playSoundByMedia(int rawId, float leftVolume, float rightVolume) {
        playSoundByMedia(rawId, leftVolume, rightVolume, null);
    }

    /**
     * 播放声音 不能同时播放多种音频
     * 消耗资源较大
     *
     * @param rawId
     */
    public static void playSoundByMedia(int rawId, float leftVolume, float rightVolume, MediaPlayer.OnCompletionListener onCompletionListener) {
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            if (null != onCompletionListener) {
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.seekTo(0);
                    }
                });
            }
            try {
                AssetFileDescriptor file = DKGlobalContext.getAppContext().getResources().openRawResourceFd(
                        rawId);
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(leftVolume, rightVolume);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static SoundPool getSoundPool(int maxStreams, int streamType, int srcQuality) {
        SoundPool soundPool = null;
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                SoundPool.Builder builder = new SoundPool.Builder();
                //传入音频的数量
                builder.setMaxStreams(maxStreams);
                //AudioAttributes是一个封装音频各种属性的类
                AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
                //设置音频流的合适属性
                attrBuilder.setLegacyStreamType(streamType);
                builder.setAudioAttributes(attrBuilder.build());
                soundPool = builder.build();
            } else {
                //第一个参数是可以支持的声音数量，第二个是声音类型，第三个是声音品质
                soundPool = new SoundPool(maxStreams, streamType, srcQuality);
            }
        } catch (Exception e) {
            if (null != soundPool) {
                soundPool.release();
            }
        }
        return soundPool;
    }

    public static SoundPool getSoundPool() {
        return getSoundPool(1, AudioManager.STREAM_SYSTEM, 0);
    }

    /**
     * 适合播放声音短，文件小
     * 可以同时播放多种音频
     * 消耗资源较小
     */
    public static void playSound(SoundPool soundPool, int rawId, int loadPriority, SoundPool.OnLoadCompleteListener onLoadCompleteListener) {
        if (null != soundPool) {
            try {
                //第一个参数Context,第二个参数资源Id，第三个参数优先级
                soundPool.load(DKGlobalContext.getAppContext(), rawId, loadPriority);
                soundPool.setOnLoadCompleteListener(onLoadCompleteListener);
                //第一个参数id，即传入池中的顺序，第二个和第三个参数为左右声道，第四个参数为优先级，第五个是否循环播放，0不循环，-1循环
                //最后一个参数播放比率，范围0.5到2，通常为1表示正常播放
                //soundPool.play(1, 1, 1, 0, 0, 1);
                //回收Pool中的资源
                //soundPool.release();
            } catch (Exception e) {
                if (null != soundPool) {
                    soundPool.release();
                }
            }
        }
    }

    /**
     * 适合播放声音短，文件小
     * 可以同时播放多种音频
     * 消耗资源较小
     */
    public static void playSound(SoundPool soundPool, int rawId) {
        if (null != soundPool) {
            try {
                //第一个参数Context,第二个参数资源Id，第三个参数优先级
                soundPool.load(DKGlobalContext.getAppContext(), rawId, 1);
                soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                        soundPool.play(1, 0.5f, 0.5f, 0, 0, 1);
                    }
                });
            } catch (Exception e) {
                if (null != soundPool) {
                    soundPool.release();
                }
            }
        }
    }

    /**
     * 适合播放声音短，文件小
     * 可以同时播放多种音频
     * 消耗资源较小
     */
    public static void playSound(int rawId) {
        playSound(getSoundPool(), rawId);
    }

    /**
     * 适合播放声音短，文件小
     * 可以同时播放多种音频
     * 消耗资源较小
     */
    public static void playSound(int rawId, float leftVolume, float rightVolume) {
        playSound(getSoundPool(), rawId, 1, new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(1, leftVolume, rightVolume, 0, 0, 1);
            }
        });
    }

    /**
     * 适合播放声音短，文件小
     * 可以同时播放多种音频
     * 消耗资源较小
     */
    public static void playSound(int rawId, int loop) {
        playSound(getSoundPool(), rawId, 1, new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(1, 0.5f, 0.5f, 0, loop, 1);
            }
        });
    }

    /**
     * 适合播放声音短，文件小
     * 可以同时播放多种音频
     * 消耗资源较小
     */
    public static void playSound(int rawId, float rate) {
        playSound(getSoundPool(), rawId, 1, new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(1, 0.5f, 0.5f, 0, 0, rate);
            }
        });
    }

    /**
     * 播放通知声音
     */
    public static void playRingTone(Uri soundUri) {
        if (null == soundUri) {
            soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone rt = RingtoneManager.getRingtone(DKGlobalContext.getAppContext(), soundUri);
        rt.play();
    }

    /**
     * 播放通知声音
     */
    public static void playRingTone(Uri soundUri, float volume, boolean looping, AudioAttributes attributes) {
        if (null == soundUri) {
            soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone rt = RingtoneManager.getRingtone(DKGlobalContext.getAppContext(), soundUri);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {//Android9.0
            rt.setVolume(volume);
            rt.setLooping(looping);
        }
        rt.setAudioAttributes(attributes);
        rt.play();
    }
}
