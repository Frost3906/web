package action;

import lombok.NoArgsConstructor;

import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ActionForward {
	private String path;
	private boolean redirect;
}
