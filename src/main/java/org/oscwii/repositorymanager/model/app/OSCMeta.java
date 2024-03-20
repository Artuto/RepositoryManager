package org.oscwii.repositorymanager.model.app;

import com.google.gson.annotations.SerializedName;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public record OSCMeta(String name, String author, String category,
                      String authorContact, List<String> supportedPlatforms,
                      List<String> peripherals, String version, EnumSet<Flag> flags,
                      Source source)
{
    public record Source(String type, Format format, String url, String file,
                         String userAgent, Set<String> additionalFiles)
    {
        public enum Format
        {
            @SerializedName("7z")
            SEVEN_ZIP(".7z"),
            @SerializedName("zip")
            ZIP      (".zip"),
            @SerializedName("bztar")
            BZTAR    (".tar.bz2", ".tbz2"),
            @SerializedName("gztar")
            GZTAR    (".tar.gz", ".tgz"),
            @SerializedName("tar")
            TAR      (".tar"),
            @SerializedName("xztar")
            XZTAR    (".tar.xz", ".txz"),
            @SerializedName("rar")
            RAR      (".rar");

            private final String[] extensions;

            Format(String... extensions)
            {
                this.extensions = extensions;
            }

            public String[] extensions()
            {
                return extensions;
            }
        }
    }

    public enum Flag
    {
        @SerializedName("writes_to_nand")
        WRITES_TO_NAND
    }
}
