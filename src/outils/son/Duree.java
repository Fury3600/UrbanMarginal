package outils.son;

import java.io.Serializable;

/**
 * Repr�sente une dur�e
 */

public class Duree implements Serializable
{
/**
 * Dur�e en microseconde
 */
  private long microsecondes;
  /**
   * Dur�e en heures, minutes, secondes et microsecondes
   */
  private int heure, minute, seconde, microseconde;
  
  /**
   * Constructeur de la classe Duree
   */
  public Duree()
  {
  }
  /**
   * Constructeur de la classe Duree avec des microsecondes type long
   * @param microsecondes dur�e en microsecondes type long
   */
  public Duree(long microsecondes)
  {
    this.microsecondes = microsecondes;
    long temps = this.microsecondes;
    this.microseconde = (int)(temps % 1000000L);
    temps = temps / 1000000L;
    this.seconde = (int)(temps % 60L);
    temps = temps / 60L;
    this.minute = (int)(temps % 60L);
    temps = temps / 60L;
    this.heure = (int)temps;
  }
  /**
   * Constructeur de la classe Duree avec des microsecondes type int
   * @param microseconde dur�e en microsecondes type int
   */
  public Duree(int microseconde)
  {
    if(microseconde < 0)
    {
      throw new IllegalArgumentException(
          "Le nombre de microsecondes ne peut pas �tre n�gative");
    }
    if(microseconde > 999999)
    {
      throw new IllegalArgumentException("Le nombre de microsecondes ne peut pas �tre plus de 999999, sinon on a des secondes");
    }
    this.microseconde = microseconde;
    this.microsecondes = (long)this.microseconde;
  }
  /**
   * Constructeur de la classe Duree avec des secondes et des microsecondes
   * @param seconde dur�e en seconde type int
   * @param microseconde dur�e en microseconde type int
   */
  public Duree(int seconde, int microseconde)
  {
    this(microseconde);
    if(seconde < 0)
    {
      throw new IllegalArgumentException(
          "Le nombre de secondes ne peut pas �tre n�gative");
    }
    if(seconde > 59)
    {
      throw new IllegalArgumentException(
          "Le nombre de secondes ne peut pas �tre plus de 59, sinon on a des minutes");
    }
    this.seconde = seconde;
    this.microsecondes += 1000000L * (long)this.seconde;
  }
  /**
   * Constructeur de la classe Duree avec des minutes, secondes et microsecondes
   * @param minute dur�e en minute type int
   * @param seconde dur�e en seconde type int
   * @param microseconde dur�e en microseconde type int
   */
  public Duree(int minute, int seconde, int microseconde)
  {
    this(seconde, microseconde);
    if(minute < 0)
    {
      throw new IllegalArgumentException(
          "Le nombre de minutes ne peut pas �tre n�gative");
    }
    if(minute > 59)
    {
      throw new IllegalArgumentException(
          "Le nombre minutes ne peut pas �tre plus de 59, sinon on a des heures");
    }
    this.minute = minute;
    this.microsecondes += 60L * 1000000L * (long)this.minute;
  }
  /**
   * Constructeur de la classe Duree avec des heures, minutes, secondes et microsecondes
   * @param heure dur�e en heure type int
   * @param minute dur�e en minute type int
   * @param seconde dur�e en seconde type int
   * @param microseconde dur�e en microseconde type int
   */
  public Duree(int heure, int minute, int seconde, int microseconde)
  {
    this(minute, seconde, microseconde);
    if(heure < 0)
    {
      throw new IllegalArgumentException(
          "Le nombre d'heure ne peut pas �tre n�gative");
    }
    this.heure = heure;
    this.microsecondes += 60L * 60L * 1000000L * (long)this.heure;
  }
  /**
   * Constructeur de la classe Duree avec une variable de type Duree
   * @param duree variable de type Duree
   */
  public Duree(Duree duree)
  {
    this.microsecondes = duree.microsecondes;
    this.microseconde = duree.microseconde;
    this.seconde = duree.seconde;
    this.minute = duree.minute;
    this.heure = duree.heure;
  }
  /**
   * Convertit en microsecondes de type long
   * @return r�sultat en microsecondes
   */
  public long enMicrosecondes()
  {
    return this.microsecondes;
  }
  /**
   * Convertit en microsecondes de type long
   * @return r�sultat en microsecondes
   */
  public long enMillisecondes()
  {
    return this.microsecondes / 1000L;
  }
  /**
   * Convertit en microsecondes de type int
   * @return r�sultat en microsecondes
   */
  public int getMicroseconde()
  {
    return this.microseconde;
  }
  /**
   * Convertit en microsecondes de type int
   * @return r�sultat en microsecondes
   */
  public int getMilliseconde()
  {
    return this.microseconde / 1000;
  }
  /**
   * Convertit en secondes
   * @return r�sultat en secondes
   */
  public int getSeconde()
  {
    return this.seconde;
  }
  /**
   * Convertit en minutes
   * @return r�sultat en minutes
   */
  public int getMinute()
  {
    return this.minute;
  }
  /**
   * Convertit en heures
   * @return r�sultat en heures
   */
  public int getHeure()
  {
    return this.heure;
  }
}
