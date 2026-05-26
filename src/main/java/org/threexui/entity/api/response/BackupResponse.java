package org.threexui.entity.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BackupResponse implements ApiResponse {

    private File file;

    @Override
    public boolean isSuccess() {
        return file != null && file.exists();
    }

    @Override
    public String getMessage() {
        return null;
    }
}