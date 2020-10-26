package com.sii.eucaptcha.voice;

import nl.captcha.audio.Sample;
import nl.captcha.audio.producer.VoiceProducer;
import nl.captcha.util.FileUtil;

import java.util.Map;

/**
 * @author mousab.aidoud
 * @version 1.0
 * Language voice producer
 */
public class LanguageVoiceProducer implements VoiceProducer {

    private final Map<String, String> voices;

    /**
     * @param voices       the map with the audiofiles for the chosen locale
     */
    public LanguageVoiceProducer(Map<String, String> voices) {
        this.voices = voices;
    }

    /**
     * Listing of the audio files for each characters , and convert the small case to upper case
     *
     * @param num the char to be converted to text
     * @return audio file for each char
     */
    @Override
    public Sample getVocalization(char num) {
        String str = String.valueOf(num).toUpperCase();

        String filename = voices.get(str);
        return FileUtil.readSample(filename);
    }

}

