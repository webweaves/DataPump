package com.scottfree.datapump;

import java.util.Objects;

public class DataStream {

    public static final String DEFAULT = "DEFAULT_STREAM";

    private String stream;

    public DataStream(String stream) {
        this.stream = stream;
    }

    public static DataStream defaultStream() {
        return new DataStream(DEFAULT);
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataStream stream2 = (DataStream) o;
        return Objects.equals(stream, stream2.stream);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stream);
    }

    @Override
    public String toString() {
        return "DataStream{stream='" + stream + "'}";
    }
}
