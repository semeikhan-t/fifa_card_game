TRUNCATE players RESTART IDENTITY CASCADE;
TRUNCATE countries RESTART IDENTITY CASCADE;

-- Countries
INSERT INTO countries (id, name, ovr_attack, ovr_defense) VALUES (1, 'Kazakhstan', 65, 68);
INSERT INTO countries (id, name, ovr_attack, ovr_defense) VALUES (2, 'Brazil', 92, 88);
INSERT INTO countries (id, name, ovr_attack, ovr_defense) VALUES (3, 'France', 90, 89);
INSERT INTO countries (id, name, ovr_attack, ovr_defense) VALUES (4, 'Argentina', 91, 87);
INSERT INTO countries (id, name, ovr_attack, ovr_defense) VALUES (5, 'England', 89, 90);
INSERT INTO countries (id, name, ovr_attack, ovr_defense) VALUES (6, 'Germany', 88, 88);
INSERT INTO countries (id, name, ovr_attack, ovr_defense) VALUES (7, 'Spain', 87, 89);
INSERT INTO countries (id, name, ovr_attack, ovr_defense) VALUES (8, 'Portugal', 89, 86);
INSERT INTO countries (id, name, ovr_attack, ovr_defense) VALUES (9, 'Netherlands', 86, 88);
INSERT INTO countries (id, name, ovr_attack, ovr_defense) VALUES (10, 'Australia', 80, 80);
INSERT INTO countries (id, name, ovr_attack, ovr_defense) VALUES (11, 'Japan', 82, 80);
INSERT INTO countries (id, name, ovr_attack, ovr_defense) VALUES (12, 'USA', 80, 81);
INSERT INTO countries (id, name, ovr_attack, ovr_defense) VALUES (13, 'Morocco', 83, 84);
INSERT INTO countries (id, name, ovr_attack, ovr_defense) VALUES (14, 'Mexico', 84, 85);
INSERT INTO countries (id, name, ovr_attack, ovr_defense) VALUES (15, 'South Korea', 85, 86);

-- Players for argentina
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('epalacios', 4, 'GK', 70, TRUE, 'argentina_e__palacios_6002.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('farmani', 4, 'DEF', 76, TRUE, 'argentina_f__armani_2463.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('gpezzella', 4, 'DEF', 73, TRUE, 'argentina_g__pezzella_2469.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('grulli', 4, 'DEF', 84, TRUE, 'argentina_g__rulli_47296.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('lautaromartnez', 4, 'DEF', 79, TRUE, 'argentina_lautaro_mart_nez_217.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('lbalerdi', 4, 'MID', 83, TRUE, 'argentina_l__balerdi_6.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('lmartnez', 4, 'MID', 86, TRUE, 'argentina_l__mart_nez_6000.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('lmessi', 4, 'MID', 74, TRUE, 'argentina_l__messi_154.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('lparedes', 4, 'FWD', 85, TRUE, 'argentina_l__paredes_271.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('macua', 4, 'FWD', 74, TRUE, 'argentina_m__acu_a_1493.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('nmolina', 4, 'FWD', 81, TRUE, 'argentina_n__molina_6503.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('nprez', 4, 'GK', 71, FALSE, 'argentina_n__p_rez_37.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ntagliafico', 4, 'DEF', 76, FALSE, 'argentina_n__tagliafico_529.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('nvalentini', 4, 'MID', 90, FALSE, 'argentina_n__valentini_311071.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('pdybala', 4, 'FWD', 89, FALSE, 'argentina_p__dybala_875.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('rdepaul', 4, 'MID', 92, FALSE, 'argentina_r__de_paul_2472.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('vcarboni', 4, 'DEF', 76, FALSE, 'argentina_v__carboni_341646.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('correa', 4, 'FWD', 85, FALSE, 'argentina____correa_53.png');

-- Players for australia
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('bslisz', 10, 'GK', 94, TRUE, 'australia_b__slisz_40534.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('bwdowik', 10, 'DEF', 80, TRUE, 'australia_b__wdowik_122259.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('dmarczuk', 10, 'DEF', 88, TRUE, 'australia_d__marczuk_271983.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('dszymaski', 10, 'DEF', 75, TRUE, 'australia_d__szyma_ski_3011.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('kstruski', 10, 'DEF', 83, TRUE, 'australia_k__struski_125646.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ktrelowski', 10, 'MID', 88, TRUE, 'australia_k__trelowski_265761.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('kurbaski', 10, 'MID', 74, TRUE, 'australia_k__urba__ski_182280.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('kwiderski', 10, 'MID', 91, TRUE, 'australia_k___widerski_2385.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mkochalski', 10, 'FWD', 89, TRUE, 'australia_m__kochalski_128640.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mskra', 10, 'FWD', 73, TRUE, 'australia_m__sk_ra__40809.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mwieteska', 10, 'FWD', 90, TRUE, 'australia_m__wieteska_40392.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('nzalewski', 10, 'GK', 70, FALSE, 'australia_n__zalewski_203474.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('pbochniewicz', 10, 'DEF', 74, FALSE, 'australia_p__bochniewicz_40224.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('pzieliski', 10, 'MID', 81, FALSE, 'australia_p__zieli_ski_329.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('swalukiewicz', 10, 'FWD', 88, FALSE, 'australia_s__walukiewicz_40582.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('tkdziora', 10, 'MID', 86, FALSE, 'australia_t__k_dziora_2164.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('tromanczuk', 10, 'DEF', 84, FALSE, 'australia_t__romanczuk_40482.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('skorupski', 10, 'FWD', 85, FALSE, 'australia____skorupski_2998.png');

-- Players for brazil
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ayrtonlucas', 2, 'GK', 81, TRUE, 'brazil_ayrton_lucas_1771.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('bento', 2, 'DEF', 78, TRUE, 'brazil_bento_10111.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('beraldo', 2, 'DEF', 75, TRUE, 'brazil_beraldo_307835.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('bremer', 2, 'DEF', 72, TRUE, 'brazil_bremer_30497.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('carlosaugusto', 2, 'DEF', 70, TRUE, 'brazil_carlos_augusto_10238.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('fabrciobruno', 2, 'MID', 84, TRUE, 'brazil_fabr_cio_bruno_10089.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('galeno', 2, 'MID', 78, TRUE, 'brazil_galeno_41197.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('guilhermearana', 2, 'MID', 84, TRUE, 'brazil_guilherme_arana_2038.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('lojardim', 2, 'FWD', 94, TRUE, 'brazil_l_o_jardim_41169.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('murilo', 2, 'FWD', 72, TRUE, 'brazil_murilo_10083.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('nino', 2, 'FWD', 79, TRUE, 'brazil_nino_10306.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('pepaquino', 2, 'GK', 84, FALSE, 'brazil_pep__aquino_10500.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('rafael', 2, 'DEF', 83, FALSE, 'brazil_rafael_10081.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('raphaelveiga', 2, 'MID', 78, FALSE, 'brazil_raphael_veiga_9920.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('rodrygo', 2, 'FWD', 78, FALSE, 'brazil_rodrygo_10009.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('vinciusjnior', 2, 'MID', 70, FALSE, 'brazil_vin_cius_j_nior_762.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('derson', 2, 'DEF', 74, FALSE, 'brazil__derson_10097.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('dermilito', 2, 'FWD', 70, FALSE, 'brazil__der_milit_o_372.png');

-- Players for england
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('bchilwell', 5, 'GK', 85, TRUE, 'england_b__chilwell_2933.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('bsaka', 5, 'DEF', 88, TRUE, 'england_b__saka_1460.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('cjones', 5, 'DEF', 84, TRUE, 'england_c__jones_293.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('cpalmer', 5, 'DEF', 92, TRUE, 'england_c__palmer_152982.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('dhenderson', 5, 'DEF', 71, TRUE, 'england_d__henderson_19088.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('drice', 5, 'MID', 80, TRUE, 'england_d__rice_2937.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('eeze', 5, 'MID', 80, TRUE, 'england_e__eze_19586.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jbellingham', 5, 'MID', 84, TRUE, 'england_j__bellingham_129718.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jbranthwaite', 5, 'FWD', 77, TRUE, 'england_j__branthwaite_17661.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jgomez', 5, 'FWD', 77, TRUE, 'england_j__gomez_284.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jgrealish', 5, 'FWD', 75, TRUE, 'england_j__grealish_19187.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jmaddison', 5, 'GK', 83, FALSE, 'england_j__maddison_18784.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jpickford', 5, 'DEF', 79, FALSE, 'england_j__pickford_2932.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jstones', 5, 'MID', 88, FALSE, 'england_j__stones_626.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jtrafford', 5, 'FWD', 79, FALSE, 'england_j__trafford_162489.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('kphillips', 5, 'MID', 82, FALSE, 'england_k__phillips_19130.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mguhi', 5, 'DEF', 86, FALSE, 'england_m__gu_hi_67971.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('pfoden', 5, 'FWD', 73, FALSE, 'england_p__foden_631.png');

-- Players for france
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('agriezmann', 3, 'GK', 71, TRUE, 'france_a__griezmann_56.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('arabiot', 3, 'DEF', 88, TRUE, 'france_a__rabiot_272.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('atchouamni', 3, 'DEF', 92, TRUE, 'france_a__tchouam_ni_1271.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('bbarcola', 3, 'DEF', 74, TRUE, 'france_b__barcola_161904.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('bsamba', 3, 'DEF', 92, TRUE, 'france_b__samba_21628.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('dupamecano', 3, 'MID', 75, TRUE, 'france_d__upamecano_1149.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ecamavinga', 3, 'MID', 74, TRUE, 'france_e__camavinga_2207.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('fmendy', 3, 'MID', 91, TRUE, 'france_f__mendy_653.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jclauss', 3, 'FWD', 72, TRUE, 'france_j__clauss_25008.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jkound', 3, 'FWD', 79, TRUE, 'france_j__kound__1257.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('kylianmbapp', 3, 'FWD', 80, TRUE, 'france_kylian_mbapp__278.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('kthuram', 3, 'GK', 88, FALSE, 'france_k__thuram_116.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('lhernndez', 3, 'DEF', 82, FALSE, 'france_l__hern_ndez_33.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mmaignan', 3, 'MID', 78, FALSE, 'france_m__maignan_22221.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('odembl', 3, 'FWD', 70, FALSE, 'france_o__demb_l__153.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('thernndez', 3, 'MID', 94, FALSE, 'france_t__hern_ndez_47300.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('wzareemery', 3, 'DEF', 77, FALSE, 'france_w__za_re_emery_336657.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('yfofana', 3, 'FWD', 75, FALSE, 'france_y__fofana_22254.png');

-- Players for germany
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('anbel', 6, 'GK', 74, TRUE, 'germany_a__n_bel_399.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('bhenrichs', 6, 'DEF', 88, TRUE, 'germany_b__henrichs_98.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('cfhrich', 6, 'DEF', 87, TRUE, 'germany_c__f_hrich_24798.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('draum', 6, 'DEF', 92, TRUE, 'germany_d__raum_25158.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('gprmel', 6, 'DEF', 92, TRUE, 'germany_g__pr_mel_24854.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jbeste', 6, 'MID', 79, TRUE, 'germany_j__beste_25313.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jblaswich', 6, 'MID', 90, TRUE, 'germany_j__blaswich_37246.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jbrandt', 6, 'MID', 71, TRUE, 'germany_j__brandt_984.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jhofmann', 6, 'FWD', 75, TRUE, 'germany_j__hofmann_25635.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jtah', 6, 'FWD', 78, TRUE, 'germany_j__tah_972.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mducksch', 6, 'FWD', 78, TRUE, 'germany_m__ducksch_25464.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mmittelstdt', 6, 'GK', 73, FALSE, 'germany_m__mittelst_dt_25342.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mneuer', 6, 'DEF', 93, FALSE, 'germany_m__neuer_497.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('nschlotterbeck', 6, 'MID', 82, FALSE, 'germany_n__schlotterbeck_26243.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('nsle', 6, 'FWD', 79, FALSE, 'germany_n__s_le_506.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('obaumann', 6, 'MID', 88, FALSE, 'germany_o__baumann_702.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('randrich', 6, 'DEF', 73, FALSE, 'germany_r__andrich_24903.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('wanton', 6, 'FWD', 86, FALSE, 'germany_w__anton_25368.png');

-- Players for japan
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('acontreras', 11, 'GK', 94, TRUE, 'japan_a__contreras_13955.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('acruz', 11, 'DEF', 88, TRUE, 'japan_a__cruz_2841.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('agamboa', 11, 'DEF', 73, TRUE, 'japan_a__gamboa_8720.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('alezcano', 11, 'DEF', 92, TRUE, 'japan_a__lezcano_178696.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('asurez', 11, 'DEF', 88, TRUE, 'japan_a__su_rez_196255.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('cmora', 11, 'MID', 85, TRUE, 'japan_c__mora_196480.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ffaerrn', 11, 'MID', 87, TRUE, 'japan_f__faerr_n_14104.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('hquirs', 11, 'MID', 94, TRUE, 'japan_h__quir_s_270487.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jbrenes', 11, 'FWD', 89, TRUE, 'japan_j__brenes_14146.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jmora', 11, 'FWD', 87, TRUE, 'japan_j__mora_50758.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jperaza', 11, 'FWD', 90, TRUE, 'japan_j__peraza_295228.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('kbriceo', 11, 'GK', 73, FALSE, 'japan_k__brice_o_13870.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ogalo', 11, 'DEF', 83, FALSE, 'japan_o__galo_13937.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('sacua', 11, 'MID', 72, FALSE, 'japan_s__acu_a_201749.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('scrdenas', 11, 'FWD', 86, FALSE, 'japan_s__c_rdenas_324113.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('wmadrigal', 11, 'MID', 78, FALSE, 'japan_w__madrigal_270498.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ymolina', 11, 'DEF', 94, FALSE, 'japan_y__molina_126654.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('yromn', 11, 'FWD', 87, FALSE, 'japan_y__rom_n_14001.png');

-- Players for kazakhstan
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('alunin', 1, 'GK', 86, TRUE, 'kazakhstan_a__lunin_47400.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('bmykhailichenko', 1, 'DEF', 90, TRUE, 'kazakhstan_b__mykhailichenko_63446.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('dpopov', 1, 'DEF', 70, TRUE, 'kazakhstan_d__popov_2167.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('driznyk', 1, 'DEF', 91, TRUE, 'kazakhstan_d__riznyk_1500.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('enazaryna', 1, 'DEF', 72, TRUE, 'kazakhstan_e__nazaryna_8458.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mmatvienko', 1, 'MID', 82, TRUE, 'kazakhstan_m__matvienko_682.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mshaparenko', 1, 'MID', 85, TRUE, 'kazakhstan_m__shaparenko_2177.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ogutsulyak', 1, 'MID', 82, TRUE, 'kazakhstan_o__gutsulyak_63543.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('okaravaev', 1, 'FWD', 91, TRUE, 'kazakhstan_o__karavaev_55877.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('opikhalyonok', 1, 'FWD', 84, TRUE, 'kazakhstan_o__pikhalyonok_63420.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('otymchyk', 1, 'FWD', 76, TRUE, 'kazakhstan_o__tymchyk_63434.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ryaremchuk', 1, 'GK', 75, FALSE, 'kazakhstan_r__yaremchuk_8493.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('vbondar', 1, 'DEF', 71, FALSE, 'kazakhstan_v__bondar_676.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('vbuyalskyi', 1, 'MID', 81, FALSE, 'kazakhstan_v__buyalskyi_2172.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('vdubinchak', 1, 'FWD', 70, FALSE, 'kazakhstan_v__dubinchak_63564.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('vtsygankov', 1, 'MID', 88, FALSE, 'kazakhstan_v__tsygankov_2182.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('vvanat', 1, 'DEF', 86, FALSE, 'kazakhstan_v__vanat_161670.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ykonoplya', 1, 'FWD', 84, FALSE, 'kazakhstan_y__konoplya_125418.png');

-- Players for mexico
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('agonzlez', 14, 'GK', 71, TRUE, 'mexico_a__gonz_lez_35546.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('cacevedo', 14, 'DEF', 81, TRUE, 'mexico_c__acevedo_35769.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('elvarez', 14, 'DEF', 83, TRUE, 'mexico_e___lvarez_51068.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ftapia', 14, 'DEF', 94, TRUE, 'mexico_f__tapia_199401.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jcarrillo', 14, 'DEF', 83, TRUE, 'mexico_j__carrillo_195704.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jcastillo', 14, 'MID', 87, TRUE, 'mexico_j__castillo_277189.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jgonzlez', 14, 'MID', 88, TRUE, 'mexico_j__gonz_lez_35993.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jlozano', 14, 'MID', 91, TRUE, 'mexico_j__lozano_127228.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jrodrguez', 14, 'FWD', 89, TRUE, 'mexico_j__rodr_guez_297145.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('lmartnezdupuy', 14, 'FWD', 71, TRUE, 'mexico_l__mart_nez_dupuy_289449.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('lolivas', 14, 'FWD', 81, TRUE, 'mexico_l__olivas_119193.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('lromo', 14, 'GK', 77, FALSE, 'mexico_l__romo_35970.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ogovea', 14, 'DEF', 81, FALSE, 'mexico_o__govea_8454.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('opineda', 14, 'MID', 85, FALSE, 'mexico_o__pineda_35576.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ralvarado', 14, 'FWD', 92, FALSE, 'mexico_r__alvarado_2879.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('rhuescas', 14, 'MID', 84, FALSE, 'mexico_r__huescas_310199.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('rmeraz', 14, 'DEF', 92, FALSE, 'mexico_r__meraz_35943.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('smuz', 14, 'FWD', 93, FALSE, 'mexico_s__mu__z_212841.png');

-- Players for morocco
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ahmedabouelfotouh', 13, 'GK', 83, TRUE, 'morocco_ahmed_abou_el_fotouh_2649.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ahmedatef', 13, 'DEF', 84, TRUE, 'morocco_ahmed_atef_196832.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ahmedramadan', 13, 'DEF', 88, TRUE, 'morocco_ahmed_ramadan_17143.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('akramtawfik', 13, 'DEF', 94, TRUE, 'morocco_akram_tawfik_17047.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('emamashour', 13, 'DEF', 72, TRUE, 'morocco_emam_ashour_17269.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('hamdifathy', 13, 'MID', 85, TRUE, 'morocco_hamdi_fathy_16813.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('kahraba', 13, 'MID', 87, TRUE, 'morocco_kahraba_16861.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('marwanattia', 13, 'MID', 71, TRUE, 'morocco_marwan_attia_190575.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mohamedelshamy', 13, 'FWD', 78, TRUE, 'morocco_mohamed_el_shamy_16991.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mohamedhamdy', 13, 'FWD', 79, TRUE, 'morocco_mohamed_hamdy_16836.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mohamedhany', 13, 'FWD', 70, TRUE, 'morocco_mohamed_hany_2654.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mohamedmagdikafsha', 13, 'GK', 91, FALSE, 'morocco_mohamed_magdi_kafsha_16843.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mohamedshokry', 13, 'DEF', 88, FALSE, 'morocco_mohamed_shokry_270354.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mohanadlasheen', 13, 'MID', 78, FALSE, 'morocco_mohanad_lasheen_16841.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mostafafathi', 13, 'FWD', 90, FALSE, 'morocco_mostafa_fathi_16862.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('omarkamal', 13, 'MID', 74, FALSE, 'morocco_omar_kamal_16885.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ramirabia', 13, 'DEF', 83, FALSE, 'morocco_rami_rabia_16805.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('zizo', 13, 'FWD', 80, FALSE, 'morocco_zizo_16871.png');

-- Players for netherlands
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('cstengs', 9, 'GK', 89, TRUE, 'netherlands_c__stengs_36910.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ddumfries', 9, 'DEF', 74, TRUE, 'netherlands_d__dumfries_226.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('fdejong', 9, 'DEF', 70, TRUE, 'netherlands_f__de_jong_538.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jbijlow', 9, 'DEF', 71, TRUE, 'netherlands_j__bijlow_37137.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jfrimpong', 9, 'DEF', 85, TRUE, 'netherlands_j__frimpong_152654.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jschouten', 9, 'MID', 82, TRUE, 'netherlands_j__schouten_37890.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jteze', 9, 'MID', 70, TRUE, 'netherlands_j__teze_231.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jveerman', 9, 'MID', 80, TRUE, 'netherlands_j__veerman_37749.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mvandeven', 9, 'FWD', 91, TRUE, 'netherlands_m__van_de_ven_152849.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('nak', 9, 'FWD', 87, TRUE, 'netherlands_n__ak__18861.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('nolij', 9, 'FWD', 94, TRUE, 'netherlands_n__olij_37818.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('qtimber', 9, 'GK', 81, FALSE, 'netherlands_q__timber_38747.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('rgravenberch', 9, 'DEF', 72, FALSE, 'netherlands_r__gravenberch_542.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('sdevrij', 9, 'MID', 89, FALSE, 'netherlands_s__de_vrij_194.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('tdallinga', 9, 'FWD', 84, FALSE, 'netherlands_t__dallinga_93016.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('tkoopmeiners', 9, 'MID', 77, FALSE, 'netherlands_t__koopmeiners_36899.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('treijnders', 9, 'DEF', 89, FALSE, 'netherlands_t__reijnders_36902.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('vvandijk', 9, 'FWD', 70, FALSE, 'netherlands_v__van_dijk_290.png');

-- Players for portugal
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('bernardosilva', 8, 'GK', 79, TRUE, 'portugal_bernardo_silva_636.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('danilopereira', 8, 'DEF', 90, TRUE, 'portugal_danilo_pereira_381.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('danymota', 8, 'DEF', 79, TRUE, 'portugal_dany_mota_30603.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('diogocosta', 8, 'DEF', 76, TRUE, 'portugal_diogo_costa_369.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('diogoleite', 8, 'DEF', 76, TRUE, 'portugal_diogo_leite_376.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('franciscoconceio', 8, 'MID', 94, TRUE, 'portugal_francisco_concei__o_161585.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('gonaloincio', 8, 'MID', 75, TRUE, 'portugal_gon_alo_in_cio_265595.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('gonaloramos', 8, 'MID', 85, TRUE, 'portugal_gon_alo_ramos_41585.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('joomrio', 8, 'FWD', 78, TRUE, 'portugal_jo_o_m_rio_41734.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('joopalhinha', 8, 'FWD', 73, TRUE, 'portugal_jo_o_palhinha_41104.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('matheusnunes', 8, 'FWD', 85, TRUE, 'portugal_matheus_nunes_41621.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('nunomendes', 8, 'GK', 75, FALSE, 'portugal_nuno_mendes_263482.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('otvio', 8, 'DEF', 85, FALSE, 'portugal_ot_vio_380.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('rafaelleo', 8, 'MID', 74, FALSE, 'portugal_rafael_le_o_22236.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('raphalguerreiro', 8, 'FWD', 88, FALSE, 'portugal_rapha_l_guerreiro_8.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ricardohorta', 8, 'MID', 82, FALSE, 'portugal_ricardo_horta_41103.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('rbendias', 8, 'DEF', 86, FALSE, 'portugal_r_ben_dias_567.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('samuelsoares', 8, 'FWD', 76, FALSE, 'portugal_samuel_soares_162595.png');

-- Players for south_korea
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('acallens', 15, 'GK', 92, TRUE, 'south_korea_a__callens_2421.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('acorzo', 15, 'DEF', 81, TRUE, 'south_korea_a__corzo_2422.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('asantamara', 15, 'DEF', 72, TRUE, 'south_korea_a__santamar_a_2423.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('breyna', 15, 'DEF', 84, TRUE, 'south_korea_b__reyna_107197.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ccceda', 15, 'DEF', 81, TRUE, 'south_korea_c__c_ceda_2415.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('dromero', 15, 'MID', 78, TRUE, 'south_korea_d__romero_195940.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('fzanelatto', 15, 'MID', 79, TRUE, 'south_korea_f__zanelatto_39859.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('gtvara', 15, 'MID', 79, TRUE, 'south_korea_g__t_vara_39800.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jcastillo', 15, 'FWD', 87, TRUE, 'south_korea_j__castillo_39792.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jgrimaldo', 15, 'FWD', 90, TRUE, 'south_korea_j__grimaldo_195098.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jrivera', 15, 'FWD', 70, TRUE, 'south_korea_j__rivera_39919.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mlpez', 15, 'GK', 81, FALSE, 'south_korea_m__l_pez_2431.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('msuccar', 15, 'DEF', 84, FALSE, 'south_korea_m__succar_63979.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('preyna', 15, 'MID', 82, FALSE, 'south_korea_p__reyna_39639.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('rsolis', 15, 'FWD', 93, FALSE, 'south_korea_r__solis_39778.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('rtapia', 15, 'MID', 91, FALSE, 'south_korea_r__tapia_2433.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('spea', 15, 'DEF', 75, FALSE, 'south_korea_s__pe_a_41448.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('flores', 15, 'FWD', 94, FALSE, 'south_korea____flores_2429.png');

-- Players for spain
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('danivivian', 7, 'GK', 82, TRUE, 'spain_dani_vivian_47278.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('davidgarca', 7, 'DEF', 80, TRUE, 'spain_david_garc_a_46653.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('fermn', 7, 'DEF', 84, TRUE, 'spain_ferm_n_340626.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('gavi', 7, 'DEF', 88, TRUE, 'spain_gavi_296667.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('gerardmoreno', 7, 'DEF', 92, TRUE, 'spain_gerard_moreno_1707.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('josgay', 7, 'MID', 88, TRUE, 'spain_jos__gay__918.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('marcosllorente', 7, 'MID', 85, TRUE, 'spain_marcos_llorente_753.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mikeloyarzabal', 7, 'MID', 91, TRUE, 'spain_mikel_oyarzabal_47323.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('nacho', 7, 'FWD', 72, TRUE, 'spain_nacho_735.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('nicowilliams', 7, 'FWD', 80, TRUE, 'spain_nico_williams_183799.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('oihansancet', 7, 'FWD', 77, TRUE, 'spain_oihan_sancet_128398.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('pedri', 7, 'GK', 83, FALSE, 'spain_pedri_133609.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('robinlenormand', 7, 'DEF', 71, FALSE, 'spain_robin_le_normand_47301.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('rodrigoriquelme', 7, 'MID', 82, FALSE, 'spain_rodrigo_riquelme_136117.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('unaisimn', 7, 'FWD', 86, FALSE, 'spain_unai_sim_n_47270.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('lexbaena', 7, 'MID', 70, FALSE, 'spain__lex_baena_182219.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('lexremiro', 7, 'DEF', 74, FALSE, 'spain__lex_remiro_47269.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('igomartnez', 7, 'FWD', 94, FALSE, 'spain___igo_mart_nez_2670.png');

-- Players for usa
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('agriezmann', 12, 'GK', 92, TRUE, 'usa_a__griezmann_56.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('arabiot', 12, 'DEF', 71, TRUE, 'usa_a__rabiot_272.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('atchouamni', 12, 'DEF', 88, TRUE, 'usa_a__tchouam_ni_1271.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('bbarcola', 12, 'DEF', 73, TRUE, 'usa_b__barcola_161904.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('bsamba', 12, 'DEF', 78, TRUE, 'usa_b__samba_21628.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('dupamecano', 12, 'MID', 71, TRUE, 'usa_d__upamecano_1149.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('ecamavinga', 12, 'MID', 90, TRUE, 'usa_e__camavinga_2207.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('fmendy', 12, 'MID', 93, TRUE, 'usa_f__mendy_653.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jclauss', 12, 'FWD', 70, TRUE, 'usa_j__clauss_25008.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('jkound', 12, 'FWD', 71, TRUE, 'usa_j__kound__1257.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('kylianmbapp', 12, 'FWD', 92, TRUE, 'usa_kylian_mbapp__278.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('kthuram', 12, 'GK', 70, FALSE, 'usa_k__thuram_116.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('lhernndez', 12, 'DEF', 86, FALSE, 'usa_l__hern_ndez_33.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('mmaignan', 12, 'MID', 75, FALSE, 'usa_m__maignan_22221.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('odembl', 12, 'FWD', 80, FALSE, 'usa_o__demb_l__153.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('thernndez', 12, 'MID', 81, FALSE, 'usa_t__hern_ndez_47300.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('wzareemery', 12, 'DEF', 93, FALSE, 'usa_w__za_re_emery_336657.png');
INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES ('yfofana', 12, 'FWD', 92, FALSE, 'usa_y__fofana_22254.png');
