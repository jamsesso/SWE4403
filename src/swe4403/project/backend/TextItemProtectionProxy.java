package swe4403.project.backend;

public class TextItemProtectionProxy extends TextItem {
  private ProxyPolicy policy;

  public TextItemProtectionProxy(String word, ProxyPolicy policy) {
    super(word);
    this.policy = policy;
  }

  @Override
  public String toString() {
    if(policy.equals(ProxyPolicy.WRITE_ONLY)) {
      throw new ProxyPolicyException("Attempted to read from a write only object.");
    }

    return super.toString();
  }

  @Override
  public void setWord(String word) {
    if(policy.equals(ProxyPolicy.READ_ONLY)) {
      throw new ProxyPolicyException("Attempted to write to a read only object.");
    }

    super.setWord(word);
  }

  public ProxyPolicy getPolicy() {
    return policy;
  }
}
