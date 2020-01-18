package org.cascadebot.cascadebot.data.objects.donation;

import com.google.gson.JsonObject;
import lombok.Getter;
import org.cascadebot.cascadebot.data.language.Language;
import org.cascadebot.cascadebot.data.language.Locale;
import org.cascadebot.cascadebot.utils.FormatUtils;

public class TimeFlag extends DataFlag {

    @Getter
    private long mills;

    private TimeFlag() {
        super();
    }

    public TimeFlag(String id, FlagScope scope) {
        super(id, scope);
    }

    @Override
    DataFlag parseFlagData(JsonObject flagDataObject) {
        mills = flagDataObject.get("time").getAsLong();
        return this;
    }

    @Override
    public String toString() {
        return "TimeFlag(id=" + getId() + ", time=" + mills + ")";
    }

    @Override
    public String getDescription(Locale locale) {
        return Language.i18n(locale, "flags." + id + ".description", FormatUtils.formatLongTimeMills(mills));
    }

}