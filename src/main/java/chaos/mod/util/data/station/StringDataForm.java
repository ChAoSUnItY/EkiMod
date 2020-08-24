package chaos.mod.util.data.station;

public class StringDataForm extends DataForm {
	private String s;

	public StringDataForm(String s) {
		this.s = s;
	}

	@Override
	public String getData() {
		return s;
	}

	@Override
	public String[] toStringArray() {
		return new String[] { s };
	}

}
