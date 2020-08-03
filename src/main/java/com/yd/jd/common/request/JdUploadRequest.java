package com.yd.jd.common.request;

import com.yd.jd.common.FileItem;
import com.yd.jd.common.response.AbstractResponse;

import java.util.Map;


public interface JdUploadRequest<T extends AbstractResponse> extends JdRequest<T> {
    Map<String, FileItem> getFileParams();
}
