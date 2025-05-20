package org.threexui.entity.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.threexui.impl.APIObject;

import java.io.File;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BackupResponse implements APIObject {

    private File file;
}
